package com.saiferwp.swplanetviewerdemo.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineContextProvider {

    @Provides
    internal fun coroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }
}