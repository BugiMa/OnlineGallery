package com.bugima.onlinegallery.ui.featurePictureDetails

sealed interface PictureDetailsEvent {
    data object OnBackClicked : PictureDetailsEvent
    data class OnUrlClicked(val url: String) : PictureDetailsEvent
}
