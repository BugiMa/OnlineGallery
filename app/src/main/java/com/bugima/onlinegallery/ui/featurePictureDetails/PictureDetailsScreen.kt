package com.bugima.onlinegallery.ui.featurePictureDetails

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bugima.onlinegallery.R
import com.bugima.onlinegallery.ui.common.component.InfoText
import com.bugima.onlinegallery.ui.common.component.InfoTextUrl
import com.bugima.onlinegallery.ui.common.component.Toolbar
import kotlinx.coroutines.flow.Flow

@Composable
fun PictureDetailsScreen(
    state: PictureDetailsViewState,
    sideEffects: Flow<PictureDetailsSideEffect>,
    navController: NavController,
    onNewEvent: (PictureDetailsEvent) -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(sideEffects) {
        sideEffects.collect { effect ->
            when (effect) {
                is PictureDetailsSideEffect.NavigateUp -> navController.navigateUp()
                is PictureDetailsSideEffect.OpenUrl -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(effect.url))
                    context.startActivity(intent)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            Toolbar(
                title = "APPLICATION",
                isBackEnabled = true,
                onBackClicked = { onNewEvent(PictureDetailsEvent.OnBackClicked) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            state.picture?.let { picture ->
                InfoContainer {
                    InfoText(
                        labelResId = R.string.picture_id,
                        description = picture.id
                    )
                }
                InfoContainer {
                    InfoText(
                        labelResId = R.string.picture_author,
                        description = picture.author
                    )
                }
                InfoContainer {
                    InfoText(
                        labelResId = R.string.picture_width,
                        description = picture.width.toString()
                    )
                }
                InfoContainer {
                    InfoText(
                        labelResId = R.string.picture_height,
                        description = picture.height.toString()
                    )
                }
                InfoContainer {
                    InfoTextUrl(
                        labelResId = R.string.picture_url,
                        url = picture.url,
                    ) { onNewEvent(PictureDetailsEvent.OnUrlClicked(picture.url)) }
                }
                InfoContainer {
                    InfoTextUrl(
                        labelResId = R.string.picture_download_url,
                        url = picture.downloadUrl,
                    ) { onNewEvent(PictureDetailsEvent.OnUrlClicked(picture.downloadUrl)) }
                }
            }
        }
    }
}

@Composable
fun InfoContainer(content: @Composable () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(12.dp)
    ) {
        content()
    }
}
