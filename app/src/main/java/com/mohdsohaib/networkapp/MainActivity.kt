package com.mohdsohaib.networkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohdsohaib.networkapp.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {

    lateinit var adapter: DataAdapter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMyData()
    }


    private fun getMyData() {
        //Create Retrofit object -> convert JSON data tp model call object
         val retrofitBuilder = Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(BASE_URL)
             .build()
             .create(Api::class.java)

        //Create a Call(callback) of model call and enqueue for processing
        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<Data>> {
            //Received data in simple model type list
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                val responseBody = response.body()

                if(responseBody != null){
                     adapter = DataAdapter(this@MainActivity, responseBody)
                     binding.recyclerviewList.layoutManager = LinearLayoutManager(this@MainActivity)
                     binding.recyclerviewList.adapter = adapter

                }
//                val myStringBuilder = StringBuilder()
//                if (responseBody != null) {
//                    for(myData in responseBody){
//                        myStringBuilder.append(myData.id)
//                        myStringBuilder.append(". ")
//                        myStringBuilder.append(myData.title)
//                        myStringBuilder.append("\n")
//                        myStringBuilder.append("\n")
//                    }
//                }
//                binding.txt.text = myStringBuilder
            }
            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
               Log.d("TAG","onFailure" + t.message)
            }
        })
    }
}
