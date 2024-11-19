package com.bugima.onlinegallery.ui.featurePictureBrowser

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.ui.common.component.CircularProgressIndicator
import com.bugima.onlinegallery.ui.common.component.FullscreenError
import com.bugima.onlinegallery.ui.common.component.PictureList
import com.bugima.onlinegallery.ui.common.component.Toolbar
import com.bugima.onlinegallery.util.AppException
import com.bugima.onlinegallery.util.navigation.PictureDetailsScreen
import com.bugima.onlinegallery.util.wrapper.Resource
import kotlinx.coroutines.flow.Flow

@Composable
fun PictureBrowserScreen(
    state: PictureBrowserViewState,
    sideEffects: Flow<PictureBrowserSideEffect>,
    navController: NavController,
    onNewEvent: (PictureBrowserEvent) -> Unit,
) {

    LaunchedEffect(Unit) {
        onNewEvent(PictureBrowserEvent.Initialize)
    }

    LaunchedEffect(sideEffects) {
        sideEffects.collect { effect ->
            when (effect) {
                is PictureBrowserSideEffect.NavigateToPictureDetails -> {
                    navController.navigate(route = PictureDetailsScreen(picture = effect.picture))
                }
            }
        }
    }

    Scaffold(
        topBar = {
            Toolbar(
                title = "APPLICATION",
                isBackEnabled = false,
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->

        when (state.pictureList) {
            is Resource.Loading -> LoadingContent()
            is Resource.Success -> SuccessContent(
                contentPadding = contentPadding,
                pictures = state.pictureList.data,
                isMoreLoading = state.isMoreLoading,
                noMorePictures = state.noMorePictures,
                onRefresh = { onNewEvent(PictureBrowserEvent.RefreshPictures) },
                onLoadMore = { onNewEvent(PictureBrowserEvent.LoadMorePictures) },
                onPictureClicked = { onNewEvent(PictureBrowserEvent.OnPictureClicked(it)) },
            )

            is Resource.Error -> ErrorContent(
                error = state.pictureList.error,
                onRefreshError = { onNewEvent(PictureBrowserEvent.RefreshPictures) }
            )
        }
    }
}

@Composable
fun LoadingContent() {
    CircularProgressIndicator(
        size = 128.dp,
        strokeWidth = 16.dp
    )
}

@Composable
fun SuccessContent(
    pictures: List<Picture>,
    isMoreLoading: Boolean,
    noMorePictures: Boolean,
    contentPadding: PaddingValues,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    onPictureClicked: (picture: Picture) -> Unit,
) {
    PictureList(
        context = LocalContext.current,
        pictures = pictures,
        isMoreLoading = isMoreLoading,
        noMorePictures = noMorePictures,
        onRefresh = { onRefresh() },
        onLoadMore = { onLoadMore() },
        onPictureClicked = { onPictureClicked(it) },
        contentPadding = contentPadding,
    )
}

@Composable
fun ErrorContent(error: AppException, onRefreshError: () -> Unit) {
    FullscreenError(error) { onRefreshError() }
}

