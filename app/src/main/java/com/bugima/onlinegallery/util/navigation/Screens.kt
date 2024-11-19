package com.bugima.onlinegallery.util.navigation

import com.bugima.onlinegallery.domain.model.Picture
import kotlinx.serialization.Serializable

@Serializable
data object PictureBrowserScreen

@Serializable
data class PictureDetailsScreen(val picture: Picture)

