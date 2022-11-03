package com.akanbi.rickandmorty.presentation.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class CoreViewModel<T : UIState, in E : UIEvent> : ViewModel() {
    abstract val state: Flow<T>
}