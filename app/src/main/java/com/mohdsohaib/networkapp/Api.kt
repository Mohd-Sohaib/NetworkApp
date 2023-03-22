package com.mohdsohaib.networkapp

import retrofit2.Call
import retrofit2.http.GET

public interface Api {

    @GET("/posts")
    fun getData() : Call<List<Data>>

}