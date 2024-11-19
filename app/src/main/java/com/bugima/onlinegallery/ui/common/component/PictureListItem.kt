package com.bugima.onlinegallery.ui.common.component

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bugima.onlinegallery.R
import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.util.Const

@Composable
fun PictureItem(
    context: Context,
    picture: Picture,
    onPictureClicked: () -> Unit
) {
    val aspectRatio = picture.width.toFloat() / picture.height.toFloat()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .background(MaterialTheme.colorScheme.background)
            .clickable { onPictureClicked() }
    ) {
        CoilImage(
            url = picture.downloadUrl,
            aspectRatio = aspectRatio,
            context = context,
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .wrapContentWidth()
                .defaultMinSize(24.dp)
                .height(24.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                text = picture.id,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
                maxLines = Const.TEXT_MAX_LINE_ONE,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun LoadingItem(
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun InfoItem(

) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.no_more_data),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.wrapContentSize()
        )
    }
}