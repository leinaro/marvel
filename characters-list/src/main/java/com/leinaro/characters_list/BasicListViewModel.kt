package com.leinaro.characters_list

import com.leinaro.architecture_tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasicListViewModel @Inject constructor() : BaseViewModel<BasicListUiModel>() {

}

object BasicListUiModel {

}

sealed class BasicListEvent {

}