package com.friendroid.nasaphotooftheday.Utills

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AlertDialog

object ConnectionDetectorUtils {
    fun NetworkCheck(context: Context): Boolean {
        val connectivity = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //If connectivity object is not null
        if (connectivity != null) { //Get network info - Mobile internet access
            val info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (info != null) { //Look for whether device is currently connected to Mobile internet
                if (info.isConnected) {
                    return true
                }
            }
        }
        return false
    }

    fun buildDialog(c: Activity): AlertDialog.Builder {
        val builder =
            AlertDialog.Builder(c)
        builder.setTitle("No Internet connection.")
        builder.setMessage("Please check your internet connection!!!Turn internet on and come back")
        builder.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss()
            c.finish()
        }
        return builder
    }
}