package com.truerewind.app.player

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import android.net.Uri
import androidx.media3.common.MediaItem

class VideoPlayerManager(context: Context) {

    val player: ExoPlayer = ExoPlayer.Builder(context).build()

    fun play() {
        player.play()
    }

    fun pause() {
        player.pause()
    }

    fun release() {
        player.release()
    }

    fun loadVideo(uri: Uri) {
        val mediaItem = MediaItem.fromUri(uri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }
}