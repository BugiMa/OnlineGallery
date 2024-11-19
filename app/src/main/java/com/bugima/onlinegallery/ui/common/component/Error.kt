package com.bugima.onlinegallery.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bugima.onlinegallery.util.AppException

@Composable
fun ImageError(
    icon: ImageVector,
    iconSize: Dp = 24.dp,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.errorContainer)
    ) {
        Image(
            imageVector = icon,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onErrorContainer),
            contentDescription = null,
            modifier = Modifier.size(iconSize),
        )
    }
}

@Composable
fun FullscreenError(error: AppException, onRefreshClicked: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.errorContainer)
            .padding(16.dp)
    ) {
        Image(
            imageVector = Icons.Rounded.Warning,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error),
            modifier = Modifier.size(64.dp),
        )
        Text(
            text = stringResource(error.titleResId),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onErrorContainer
        )
        Text(
            text = stringResource(error.descResId),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onErrorContainer
        )
        if (error is AppException.NetworkException) {
            CommonSpinningIconButton(
                icon = Icons.Rounded.Refresh,
                color = MaterialTheme.colorScheme.error,
                iconColor = MaterialTheme.colorScheme.onErrorContainer
            ) { onRefreshClicked() }
        }
    }

}