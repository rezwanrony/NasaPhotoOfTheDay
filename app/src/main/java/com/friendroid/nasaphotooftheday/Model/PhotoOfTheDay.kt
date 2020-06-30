package com.friendroid.nasaphotooftheday.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PhotoOfTheDay {
    @SerializedName("date")
    var date: String? = null
    @SerializedName("explanation")
    var explanation: String? = null
    @SerializedName("hdurl")
    var hdurl: String? = null
    @SerializedName("media_type")
    var mediaType: String? = null
    @SerializedName("service_version")
    var serviceVersion: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("url")
    var url: String? = null

}