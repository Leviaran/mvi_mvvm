package me.randyarba.base_library.base.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.randyarba.base_library.base.presentation.extension.asLiveData
import kotlin.properties.Delegates

abstract class BaseViewModel<ViewState: BaseViewState, ViewAction : BaseAction>(initState: ViewState) : ViewModel() {
    private val stateMutLiveData = MutableLiveData<ViewState>()
    val stateLiveData = stateMutLiveData.asLiveData()

    protected var state by Delegates.observable(initState){_, old, new ->
        stateMutLiveData.value = new
        if(new != old) {

        }
    }

    fun sendAction(viewAction: ViewAction) {
        state = onReduceState(viewAction)
    }

    fun loadData() {
        onLoadData()
    }

    protected open fun onLoadData() {}

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState
}

interface BaseViewState

interface BaseAction