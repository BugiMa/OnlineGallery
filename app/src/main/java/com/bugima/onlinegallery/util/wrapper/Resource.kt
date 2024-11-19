package com.bugima.onlinegallery.util.wrapper

import com.bugima.onlinegallery.util.AppException

sealed interface Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>
    data class Error(val error: AppException) : Resource<Nothing>
    data object Loading : Resource<Nothing>
}