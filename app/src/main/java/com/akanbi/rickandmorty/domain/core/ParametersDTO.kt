package com.akanbi.rickandmorty.domain.core

class ParametersDTO(block: ParametersDTO.() -> Unit) {
    private val params: HashMap<String, Any> = hashMapOf()

    init {
        block.invoke(this)
    }

    fun add(key: String, value: Any) {
        params[key] = value
    }

    private fun build() = params

    fun value(key: String) = build()[key].toString()

    fun valueAsList(key: String) = build()[key] as List<*>
}