package com.akanbi.rickandmorty.common

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class ProviderContext {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val io: CoroutineContext by lazy { Dispatchers.IO }
}