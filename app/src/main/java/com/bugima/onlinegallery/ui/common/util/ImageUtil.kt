package com.bugima.onlinegallery.ui.common.util

import android.content.Context
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.ImageRequest
import coil3.request.crossfade

fun createImageLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context, 0.25)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("coil_image_cache"))
                .maxSizeBytes(50 * 1024 * 1024)
                .build()
        }
        .crossfade(true)
        .build()
}

fun createImageRequest(context: Context, url: String, aspectRatio: Float): ImageRequest {
    val imageWidth = context.resources.displayMetrics.widthPixels
    val imageHeight = (imageWidth / aspectRatio).toInt()

    return ImageRequest.Builder(context)
        .data(url)
        .size(width = imageWidth, height = imageHeight)
        .crossfade(enable = true)
        .build()
}