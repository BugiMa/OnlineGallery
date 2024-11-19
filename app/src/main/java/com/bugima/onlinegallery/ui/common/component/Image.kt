package com.bugima.onlinegallery.ui.common.component

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.bugima.onlinegallery.ui.common.util.createImageLoader
import com.bugima.onlinegallery.ui.common.util.createImageRequest

@Composable
fun CoilImage(
    context: Context,
    url: String,
    aspectRatio: Float,
) {
    SubcomposeAsyncImage(
        model = createImageRequest(
            context = context,
            url = url,
            aspectRatio = aspectRatio,
        ),
        imageLoader = createImageLoader(context),
        contentDescription = null,
        loading = { CircularProgressIndicator() },
        error = {
            ImageError(
                icon = Icons.Rounded.Warning,
                iconSize = 64.dp,
            )
        },
    )
}
