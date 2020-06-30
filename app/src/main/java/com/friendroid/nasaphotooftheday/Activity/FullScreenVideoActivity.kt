package com.friendroid.nasaphotooftheday.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.friendroid.nasaphotooftheday.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_full_screen_video.*


class FullScreenVideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_video)
        lifecycle.addObserver(youtube_player_views)

        youtube_player_views.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = intent.getStringExtra("videoId")
                youTubePlayer.loadVideo(videoId, 0f)
                youtube_player_views.toggleFullScreen()
            }
        })
    }


    override fun onStop() {
        youtube_player_views.release()
        super.onStop()
    }

    override fun onDestroy() {
        youtube_player_views.release()
        super.onDestroy()
    }
}
