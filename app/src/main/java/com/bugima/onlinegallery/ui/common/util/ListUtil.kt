package com.bugima.onlinegallery.ui.common.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun LazyListState.ScrollEndCallback(
    isMoreLoading: Boolean,
    threshold: Int = 2,
    callback: () -> Unit
) {
    val stableCallback = rememberUpdatedState(callback)

    val shouldLoadMore = remember {
        derivedStateOf {
            val totalItemsCount = layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex >= (totalItemsCount - threshold) && !isMoreLoading
        }
    }

    LaunchedEffect(this) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                stableCallback.value()
            }
    }
}

@Composable
fun LazyListState.isFirstItemVisible(): Boolean {
    return remember {
        derivedStateOf {
            firstVisibleItemIndex == 0
        }
    }.value
}