package com.bugima.onlinegallery.ui.common.component

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.ui.common.util.ScrollEndCallback
import com.bugima.onlinegallery.ui.common.util.isFirstItemVisible
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PictureList(
    context: Context,
    pictures: List<Picture>,
    isMoreLoading: Boolean,
    noMorePictures: Boolean,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    onPictureClicked: (picture: Picture) -> Unit,
    contentPadding: PaddingValues,
) {
    val coroutineScope = rememberCoroutineScope()
    val isRefreshing = remember { mutableStateOf(false) }
    val refreshState = rememberPullToRefreshState()
    val scrollState = rememberLazyListState().apply {
        ScrollEndCallback(isMoreLoading = isMoreLoading) { onLoadMore() }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing.value,
        onRefresh = { onRefresh() },
        state = refreshState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(contentPadding)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = scrollState,
        ) {
            items(items = pictures) { picture ->
                PictureItem(
                    context = context,
                    picture = picture,
                    onPictureClicked = { onPictureClicked(picture) },
                )
            }
            if (isMoreLoading) {
                item { LoadingItem() }
            }
            if (noMorePictures) {
                item { InfoItem() }
            }
        }

        AnimatedVisibility(
            visible = !scrollState.isFirstItemVisible(),
            enter = scaleIn( // TODO: Move to util
                initialScale = 0f,
                animationSpec = tween(durationMillis = 500)
            ),
            exit = scaleOut(
                targetScale = 0f,
                animationSpec = tween(durationMillis = 500)
            ),
            modifier = Modifier
                .padding(end = 24.dp, bottom = 48.dp)
                .align(Alignment.BottomEnd)
        ) {
            IconFAB(
                icon = Icons.Rounded.KeyboardArrowUp,
                onClick = {
                    coroutineScope.launch {
                        scrollState.scrollToItem(0)
                    }
                }
            )
        }
    }
}
