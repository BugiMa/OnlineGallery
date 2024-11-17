package com.bugima.onlinegallery.util

import com.bugima.onlinegallery.R

abstract class AppException(
    open val originalException: Exception? = null,
    open val titleResId: Int,
    open val descResId: Int,
) : Exception() {

    data class NetworkException(override val originalException: Exception) : AppException(
        originalException = originalException,
        titleResId = R.string.network_error_title,
        descResId = R.string.network_error_desc,
    )

    data class ServerException(override val originalException: Exception) : AppException(
        originalException = originalException,
        titleResId = R.string.server_error_title,
        descResId = R.string.server_error_desc,
    )

    // tofo: rework
    data object NoMoreDataException : AppException(
        originalException = null,
        titleResId = -1,
        descResId = -1
    ) {
        private fun readResolve(): Any = NoMoreDataException
    }

    data class UnknownException(override val originalException: Exception) : AppException(
        originalException = originalException,
        titleResId = R.string.unknown_error_title,
        descResId = R.string.unknown_error_desc,
    )
}