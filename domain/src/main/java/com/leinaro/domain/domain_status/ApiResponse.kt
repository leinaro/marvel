package com.leinaro.domain.domain_status

sealed class ApiResponse<out R>(val status: ApiStatus, val data: R?, val message: String?) {
  data class Success<out T>(val _data: T?) : ApiResponse<T>(
    status = ApiStatus.Success,
    data = _data,
    message = null,
  )

  data class Error(val exception: Exception?, val _message: String?) : ApiResponse<Nothing>(
    status = ApiStatus.Error,
    data = null,
    message = _message,
  )

  data class Loading<out R>(val _data: R? = null, val isLoading: Boolean) : ApiResponse<R>(
    status = ApiStatus.Loading,
    data = _data,
    message = null
  )
}