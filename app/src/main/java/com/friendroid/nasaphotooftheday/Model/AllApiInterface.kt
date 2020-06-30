package com.friendroid.nasaphotooftheday.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AllApiInterface {
    @GET("apod?")
    fun getData(@Query("api_key") api_key: String): Call<PhotoOfTheDay>

    @GET("apod?")
    fun getDataWithDate(@Query("api_key") api_key: String,@Query("date") date: String): Call<PhotoOfTheDay>
}