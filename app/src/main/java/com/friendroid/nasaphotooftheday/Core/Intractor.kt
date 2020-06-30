package com.friendroid.nasaphotooftheday.Core

import android.content.Context
import android.util.Log
import com.friendroid.nasaphotooftheday.Core.GetDataReport.onGetDataListener
import com.friendroid.nasaphotooftheday.Model.AllApiInterface
import com.friendroid.nasaphotooftheday.Model.PhotoOfTheDay
import com.friendroid.nasaphotooftheday.Utills.Utility
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class Intractor(private val mOnGetDatalistener: onGetDataListener,private val mOnGetDatalistenerWithDate: GetDataReport.onGetDataListenerWithDate) : GetDataReport.Interactor {
    var allphotoofthedayData= ArrayList<PhotoOfTheDay?>()
    var allphotoofthedayDataWithDate= ArrayList<PhotoOfTheDay?>()

    override fun initRetrofitCall(
        context: Context?,
        url: String?
    ) {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val request: AllApiInterface = retrofit.create(AllApiInterface::class.java)
        val call: retrofit2.Call<PhotoOfTheDay> = request.getData(Utility.API_KEY)
        call.enqueue(object : Callback<PhotoOfTheDay> {
            override fun onResponse(call: Call<PhotoOfTheDay>, response: Response<PhotoOfTheDay>) {
                if (response.code() == 200) {
                    val photoofthedayResponse = response.body()!!
                    val photoOfTheDay=PhotoOfTheDay()
                    photoOfTheDay.explanation=photoofthedayResponse.explanation
                    photoOfTheDay.hdurl=photoofthedayResponse.hdurl
                    photoOfTheDay.mediaType=photoofthedayResponse.mediaType
                    photoOfTheDay.title=photoofthedayResponse.title
                    photoOfTheDay.url=photoofthedayResponse.url
                    allphotoofthedayData.add(photoOfTheDay)
                    mOnGetDatalistener.onSuccess("List Size: " + allphotoofthedayData.size, allphotoofthedayData)
                }
            }
            override fun onFailure(call: Call<PhotoOfTheDay>, t: Throwable) {
                Log.v("Error", t.message)
                mOnGetDatalistener.onFailure(t.message)
            }
        })
    }

    override fun initRetrofitCallwithDate(context: Context?, url: String?, date: String?) {
        allphotoofthedayDataWithDate.clear()
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val request: AllApiInterface = retrofit.create(AllApiInterface::class.java)
        val call: retrofit2.Call<PhotoOfTheDay> = request.getDataWithDate(Utility.API_KEY,date!!)
        call.enqueue(object : Callback<PhotoOfTheDay> {
            override fun onResponse(call: Call<PhotoOfTheDay>, response: Response<PhotoOfTheDay>) {
                if (response.code() == 200) {
                    val photoofthedayResponse = response.body()!!
                    val photoOfTheDay=PhotoOfTheDay()
                    photoOfTheDay.explanation=photoofthedayResponse.explanation
                    photoOfTheDay.hdurl=photoofthedayResponse.hdurl
                    photoOfTheDay.mediaType=photoofthedayResponse.mediaType
                    photoOfTheDay.title=photoofthedayResponse.title
                    photoOfTheDay.url=photoofthedayResponse.url
                    allphotoofthedayDataWithDate.add(photoOfTheDay)
                    mOnGetDatalistenerWithDate.onSuccessWithDate("List Size: " + allphotoofthedayDataWithDate.size, allphotoofthedayDataWithDate)
                }
            }
            override fun onFailure(call: Call<PhotoOfTheDay>, t: Throwable) {
                Log.v("Error", t.message)
                mOnGetDatalistenerWithDate.onFailureWithDate(t.message)
            }
        })
    }


}