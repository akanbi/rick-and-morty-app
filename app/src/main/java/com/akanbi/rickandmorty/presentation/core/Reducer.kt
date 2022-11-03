package com.akanbi.rickandmorty.presentation.core

import com.akanbi.rickandmorty.BuildConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer<S : UIState, E : UIEvent>(initialVal: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialVal)
    val state: StateFlow<S>
        get() = _state

    val stateMachine: StateManager<S> = StateMachine { storedState ->
        _state.tryEmit(storedState)
    }

    init {
        stateMachine.addState(initialVal)
    }

    fun sendEvent(event: E) {
        reduce(_state.value, event)
    }

    fun setState(newState: S) {
        val success = _state.tryEmit(newState)

        if (BuildConfig.DEBUG && success) {
            stateMachine.addState(newState)
        }
    }

    abstract fun reduce(oldState: S, event: E)
}