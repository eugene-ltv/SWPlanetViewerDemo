package com.saiferwp.swplanetviewerdemo.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

abstract class FlowUseCase<in P, out R>(private val coroutineContext: CoroutineContext) {
    open operator fun invoke(parameters: P): Flow<R> {
        return execute(parameters)
            .flowOn(coroutineContext)
    }

    protected abstract fun execute(parameters: P): Flow<R>
}
