package com.bugima.onlinegallery.ui.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.bugima.onlinegallery.util.Const

@Composable
fun InfoText(
    labelResId: Int,
    description: String
) {
    Text(
        text = stringResource(labelResId, description),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
        maxLines = Const.TEXT_MAX_LINE_ONE,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.wrapContentSize()
    )
}

@Composable
fun InfoTextUrl(
    labelResId: Int,
    url: String,
    onUrlClicked: () -> Unit
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                SpanStyle(color = MaterialTheme.colorScheme.onSurface)
            ) { append(stringResource(labelResId)) }
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            ) { append(url) }
        },
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
        maxLines = Const.TEXT_MAX_LINE_TWO,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.clickable { onUrlClicked() }
    )
}