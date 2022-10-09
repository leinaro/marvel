package com.leinaro.core

sealed class Result<out R>(val status: ApiStatus, val data: R?, val message: String?) {
    data class Success<out T>(val _data: T?) : Result<T>(
        status = ApiStatus.Success,
        data = _data,
        message = null,
    )

    data class Error(val exception: Exception?, val _message: String?) : Result<Nothing>(
        status = ApiStatus.Error,
        data = null,
        message = _message,
    )

    data class Loading<out R>(val isLoading: Boolean) : Result<R>(
        status = ApiStatus.Loading,
        data = null,
        message = null
    )
}