package com.saiferwp.swplanetviewerdemo.core

interface BuildConfigFieldsProvider {
    fun get(): BuildConfigFields
}

data class BuildConfigFields(
    val isDebug: Boolean
)