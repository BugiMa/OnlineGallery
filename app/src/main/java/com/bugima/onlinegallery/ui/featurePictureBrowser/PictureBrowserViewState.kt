package com.bugima.onlinegallery.ui.featurePictureBrowser

import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.util.wrapper.Resource

data class PictureBrowserViewState(
    val pictureList: Resource<List<Picture>> = Resource.Loading,
    val isMoreLoading: Boolean = false,
    val noMorePictures: Boolean = false,
)
