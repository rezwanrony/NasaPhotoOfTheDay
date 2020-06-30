package com.friendroid.nasaphotooftheday.Core

import android.content.Context
import com.friendroid.nasaphotooftheday.Model.PhotoOfTheDay

interface GetDataReport {
    interface View {
        fun onGetDataSuccess(
            message: String?,
            list: List<PhotoOfTheDay?>?
        )

        fun onGetDataFailure(message: String?)

        fun onGetDataSuccessWithDate(
            message: String?,
            list: List<PhotoOfTheDay?>?
        )

        fun onGetDataFailureWithDate(message: String?)
    }

    interface Presenter {
        fun getDataFromURL(context: Context?, url: String?)
        fun getDataWithDateFromURL(context: Context?, url: String?,date:String?)
    }

    interface Interactor {
        fun initRetrofitCall(
            context: Context?,
            url: String?
        )

        fun initRetrofitCallwithDate(
            context: Context?,
            url: String?,
            date:String?
        )
    }

    interface onGetDataListener {
        fun onSuccess(
            message: String?,
            list: List<PhotoOfTheDay?>?
        )

        fun onFailure(message: String?)

    }

    interface onGetDataListenerWithDate {
        fun onSuccessWithDate(
            message: String?,
            list: List<PhotoOfTheDay?>?
        )

        fun onFailureWithDate(message: String?)

    }

}