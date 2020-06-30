package com.friendroid.nasaphotooftheday.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.friendroid.nasaphotooftheday.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
             // This method will be executed once the timer is over
                val i = Intent(this@SplashScreenActivity, PhotoOfTheDayActivity::class.java)
                startActivity(i)
                finish()
        }, 5000)
    }
}