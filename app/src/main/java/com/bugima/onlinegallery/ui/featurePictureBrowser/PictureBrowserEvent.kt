package com.bugima.onlinegallery.ui.featurePictureBrowser

import com.bugima.onlinegallery.domain.model.Picture

sealed interface PictureBrowserEvent {
    data object Initialize : PictureBrowserEvent
    data object LoadMorePictures : PictureBrowserEvent
    data object RefreshPictures : PictureBrowserEvent
    data class OnPictureClicked(val picture: Picture) : PictureBrowserEvent
}