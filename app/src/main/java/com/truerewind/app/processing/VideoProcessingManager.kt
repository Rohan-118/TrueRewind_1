package com.truerewind.app.processing

import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class VideoProcessingManager {

    suspend fun processVideo(inputUri: Uri): Uri {

        // Simulate heavy processing
        return withContext(Dispatchers.IO) {

            delay(4000) // temporary simulation (4 seconds)

            // For now we return the same video
            inputUri
        }
    }
}