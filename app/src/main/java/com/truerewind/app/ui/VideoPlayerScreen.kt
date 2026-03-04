package com.truerewind.app.ui


import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.media3.ui.PlayerView
import com.truerewind.app.player.VideoPlayerManager

@Composable
fun VideoPlayerScreen(
    videoPlayerManager: VideoPlayerManager,
    modifier: Modifier = Modifier
) {

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->

            PlayerView(context).apply {
                player = videoPlayerManager.player
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }

        }
    )
}