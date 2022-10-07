package com.leinaro.core

sealed class ErrorViewData(val error: Throwable)
data class DefaultError(val throwable: Throwable) : ErrorViewData(throwable)
