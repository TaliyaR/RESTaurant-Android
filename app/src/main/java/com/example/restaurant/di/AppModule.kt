package com.example.restaurant.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun provideCiceroneRouter(
        cicerone: Cicerone<Router>
    ): Router = cicerone.router

    @Singleton
    @Provides
    fun provideCiceroneNavigatorHolder(
        cicerone: Cicerone<Router>
    ): NavigatorHolder = cicerone.navigatorHolder
}