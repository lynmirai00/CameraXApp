package com.example.cameraxapp

import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.cameraxapp.databinding.ActivityVideoBinding
import androidx.core.net.toUri

class VideoActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        val videoUriString = intent.getStringExtra("video_uri")
        val videoUri = videoUriString?.toUri()

        val videoView = viewBinding.videoView
        videoView.setVideoURI(videoUri)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.setOnPreparedListener { it.isLooping = true }
        videoView.start()
    }
}