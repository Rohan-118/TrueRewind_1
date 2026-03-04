package com.truerewind.app

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.truerewind.app.player.VideoPlayerManager
import com.truerewind.app.ui.VideoPlayerScreen

class MainActivity : ComponentActivity() {

    private lateinit var videoPlayerManager: VideoPlayerManager

    // Video picker (opens Android file chooser)
    private val videoPicker =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                videoPlayerManager.loadVideo(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize player
        videoPlayerManager = VideoPlayerManager(this)

        // Set UI
        setContent {
            VideoPlayerScreen(videoPlayerManager)
        }

        // Launch file picker
        videoPicker.launch("video/*")
    }

    override fun onDestroy() {
        super.onDestroy()

        // Release player to prevent memory leaks
        videoPlayerManager.release()
    }
}