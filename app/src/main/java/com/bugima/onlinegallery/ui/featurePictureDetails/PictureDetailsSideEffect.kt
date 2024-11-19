package com.bugima.onlinegallery.ui.featurePictureDetails

sealed class PictureDetailsSideEffect {
    data object NavigateUp : PictureDetailsSideEffect()
    data class OpenUrl(val url: String) : PictureDetailsSideEffect()
}