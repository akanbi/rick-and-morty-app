package com.akanbi.rickandmorty.common.presentation

import android.view.View.VISIBLE
import androidx.lifecycle.*

class FlowState<D>(
    val status: Status,
    val data: D? = null,
    val error: Throwable? = null,
    val resources: Int = 0,
    val loadingVisibility: Int = VISIBLE
) {
    enum class Status {
        LOADING, SUCCESS, ERROR, FLOW_PRIMARY, FLOW_SECONDARY
    }
}

fun <D> MutableLiveData<FlowState<D>>.postLoading(state: Int) {
    value = FlowState(FlowState.Status.LOADING, loadingVisibility = state)
}

fun <D> MutableLiveData<FlowState<D>>.postSuccess(data: D?, loadingVisibility: Int = 0) {
    value = FlowState(FlowState.Status.SUCCESS, data = data, loadingVisibility = loadingVisibility)
}

fun <D> MutableLiveData<FlowState<D>>.postSuccess(data: D?) {
    value = FlowState(FlowState.Status.SUCCESS, data = data)
}

fun <D> MutableLiveData<FlowState<D>>.postError(error: Throwable) {
    value = FlowState(FlowState.Status.ERROR, error = error)
}

fun <D> MutableLiveData<FlowState<D>>.postError(resources: Int) {
    value = FlowState(FlowState.Status.ERROR, resources = resources)
}

fun <D> MutableLiveData<FlowState<D>>.postFlowPrimary() {
    value = FlowState(FlowState.Status.FLOW_PRIMARY)
}

fun <D> MutableLiveData<FlowState<D>>.postFlowSecondary() {
    value = FlowState(FlowState.Status.FLOW_SECONDARY)
}

fun <D> LiveData<FlowState<D>>.observerEvent(
    lifecycleOwner: LifecycleOwner,
    onLoading: (Int) -> Unit = {},
    onSuccess: (D) -> Unit = {},
    onError: (Throwable) -> Unit = {},
    onErrorWithId: ((Int) -> Unit)? = null,
    onFlowPrimary: (() -> Unit)? = null,
    onFlowSecondary: (() -> Unit)? = null
) {
    observe(lifecycleOwner, Observer {
        when (it.status) {
            FlowState.Status.LOADING -> {
                onLoading.invoke(it.loadingVisibility)
            }
            FlowState.Status.SUCCESS -> {
                it.data?.let { data -> onSuccess.invoke(data) }
            }
            FlowState.Status.ERROR -> {
                if (it.resources != 0)
                    it.resources.let { error -> onErrorWithId?.invoke(error) }
                else
                    it.error?.let { error ->
                        onError.invoke(error)
                    }
            }
            FlowState.Status.FLOW_PRIMARY -> onFlowPrimary?.invoke()
            FlowState.Status.FLOW_SECONDARY -> onFlowSecondary?.invoke()
        }
    })
}

fun <T : ViewModel> T.createFactory(): ViewModelProvider.Factory {
    val viewModel = this
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
    }
}

fun <T> MutableLiveData<MutableList<T>>.removeItemAt(index: Int) {
    if (!this.value.isNullOrEmpty()) {
        val oldValue = this.value
        oldValue?.removeAt(index)
        this.value = oldValue
    } else {
        this.value = mutableListOf()
    }
}

fun <T> MutableLiveData<MutableList<T>>.addNewItem(item: T?) {
    val oldValue = this.value ?: mutableListOf()
    item?.let { oldValue.add(it) }
    this.value = oldValue
}