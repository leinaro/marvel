package com.leinaro.android_architecture_tools

interface ViewHandler<T, C> {
    fun T.perform(context: Any, viewModel: C)
}
