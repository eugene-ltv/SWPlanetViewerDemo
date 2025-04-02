package com.saiferwp.swplanetviewerdemo.planets.di

import com.saiferwp.swplanetviewerdemo.planets.mapper.PlanetsEntityListToPlanetsListMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object PlanetsModuleProvider {

    @Provides
    internal fun providePlanetsEntityListToPlanetsListMapper(): PlanetsEntityListToPlanetsListMapper {
        return PlanetsEntityListToPlanetsListMapper()
    }
}