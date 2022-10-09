package com.leinaro.core

sealed class ApiStatus {
    object Success : ApiStatus()
    object Error : ApiStatus()
    object Loading : ApiStatus()
}
