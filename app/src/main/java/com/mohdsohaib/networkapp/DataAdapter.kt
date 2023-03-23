package com.mohdsohaib.networkapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class DataAdapter(private val context: Context, private val list : List<Data>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         var user_id = itemView.findViewById<TextView>(R.id.txt_id)
         var user_title = itemView.findViewById<TextView>(R.id.txt_title)
         var user_body = itemView.findViewById<TextView>(R.id.txt_body)
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
         val view = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false)
         return DataViewHolder(view)
     }

     override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
         val data = list[position]
         holder.user_id.text = data.id.toString()
         holder.user_title.text = data.title.toString()
         holder.user_body.text = data.body.toString()
     }

     override fun getItemCount(): Int {
        return list.size
     }
 }