package com.saiferwp.swplanetviewerdemo.planets.di

import android.content.Context
import com.saiferwp.swplanetviewerdemo.planets.mapper.PlanetsResponseToPlanetsListUiStateMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object PlanetsModuleProvider {

    @Provides
    internal fun providePlanetsEntityListToPlanetsListMapper(
        @ApplicationContext appContext: Context
    ): PlanetsResponseToPlanetsListUiStateMapper {
        return PlanetsResponseToPlanetsListUiStateMapper(
            context = appContext
        )
    }
}