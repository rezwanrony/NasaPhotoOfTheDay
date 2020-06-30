package com.friendroid.nasaphotooftheday.Core

import android.content.Context
import com.friendroid.nasaphotooftheday.Core.GetDataReport.onGetDataListener
import com.friendroid.nasaphotooftheday.Model.PhotoOfTheDay

class Presenter(private val mGetDataView: GetDataReport.View) :
    GetDataReport.Presenter, onGetDataListener,GetDataReport.onGetDataListenerWithDate {
    private val mIntractor: Intractor
    override fun getDataFromURL(
        context: Context?,
        url: String?
    ) {
        mIntractor.initRetrofitCall(context, url)
    }

    override fun getDataWithDateFromURL(context: Context?, url: String?, date: String?) {
        mIntractor.initRetrofitCallwithDate(context,url,date)
    }

    override fun onSuccess(message: String?, list: List<PhotoOfTheDay?>?) {
        mGetDataView.onGetDataSuccess(message, list)
    }

    override fun onFailure(message: String?) {
        mGetDataView.onGetDataFailure(message)
    }

    override fun onSuccessWithDate(message: String?, list: List<PhotoOfTheDay?>?) {
        mGetDataView.onGetDataSuccessWithDate(message,list)
    }

    override fun onFailureWithDate(message: String?) {
        mGetDataView.onGetDataFailureWithDate(message)
    }

    init {
        mIntractor = Intractor(this,this)
    }
}