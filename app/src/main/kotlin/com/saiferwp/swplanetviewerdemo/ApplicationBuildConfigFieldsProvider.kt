package com.saiferwp.swplanetviewerdemo

import com.saiferwp.swplanetviewerdemo.core.BuildConfigFields
import com.saiferwp.swplanetviewerdemo.core.BuildConfigFieldsProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

class ApplicationBuildConfigFieldsProvider : BuildConfigFieldsProvider {

    override fun get(): BuildConfigFields = BuildConfigFields(
        isDebug = BuildConfig.DEBUG
    )
}

@Module
@InstallIn(SingletonComponent::class)
object MainActivityModule {
    @Provides
    internal fun provideBuildConfigFieldsProvider(): BuildConfigFieldsProvider =
        ApplicationBuildConfigFieldsProvider()
}