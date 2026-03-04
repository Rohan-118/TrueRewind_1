package com.truerewind.app

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import com.truerewind.app.player.VideoPlayerManager
import com.truerewind.app.processing.VideoProcessingManager
import com.truerewind.app.ui.ProcessingScreen
import com.truerewind.app.ui.VideoPlayerScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var videoPlayerManager: VideoPlayerManager
    private val processingManager = VideoProcessingManager()

    private var selectedVideo: Uri? = null

    private val videoPicker =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedVideo = it
                startProcessing(it)
            }
        }

    private var uiState: MutableState<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        videoPlayerManager = VideoPlayerManager(this)

        setContent {

            val state = remember { mutableStateOf("IDLE") }
            uiState = state

            when (state.value) {

                "PROCESSING" -> ProcessingScreen()

                "PLAYER" -> VideoPlayerScreen(videoPlayerManager)

            }
        }

        videoPicker.launch("video/*")
    }

    private fun startProcessing(uri: Uri) {

        uiState?.value = "PROCESSING"

        lifecycleScope.launch {

            val processedVideo = processingManager.processVideo(uri)

            videoPlayerManager.loadVideo(processedVideo)

            uiState?.value = "PLAYER"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoPlayerManager.release()
    }
}