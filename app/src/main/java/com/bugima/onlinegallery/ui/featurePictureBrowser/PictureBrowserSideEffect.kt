package com.bugima.onlinegallery.ui.featurePictureBrowser

import com.bugima.onlinegallery.domain.model.Picture

sealed class PictureBrowserSideEffect {
    data class NavigateToPictureDetails(val picture: Picture) : PictureBrowserSideEffect()
}
