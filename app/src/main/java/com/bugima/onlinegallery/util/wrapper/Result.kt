package com.bugima.onlinegallery.util.wrapper

import com.bugima.onlinegallery.util.AppException

sealed interface Result<out T> {
    data class Success<out T>(val data: T): Result<T>
    data class Failure(val exception: AppException): Result<Nothing>
}
