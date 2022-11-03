package com.akanbi.rickandmorty.presentation.core

interface StateManager<S : UIState> {
    fun addState(state: S)
    fun selectState(position: Int)
    fun getStates(): List<S>
}

class StateMachine<S : UIState>(
    private val onStateSelected: (S) -> Unit
) : StateManager<S> {

    private val states = mutableListOf<S>()

    override fun addState(state: S) {
        states.add(state)
    }

    override fun selectState(position: Int) {
        onStateSelected(states[position])
    }

    override fun getStates(): List<S> {
        return states
    }
}