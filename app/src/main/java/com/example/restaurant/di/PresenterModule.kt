package com.example.restaurant.di

import com.example.restaurant.presenter.current.CurrentFragmentPresenter
import com.example.restaurant.presenter.main.MainActivityPresenter
import com.example.restaurant.presenter.qr.QrActivityPresenter
import com.example.restaurant.repositories.ClientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.terrakok.cicerone.Router

@Module
@InstallIn(SingletonComponent::class)
object PresenterModule {

    @Provides
    fun provideCurrentPresenter(clientRepository: ClientRepository): CurrentFragmentPresenter {
        return CurrentFragmentPresenter(clientRepository)
    }

    @Provides
    fun provideQrPresenter(
        clientRepository: ClientRepository
    ): QrActivityPresenter {
        return QrActivityPresenter(clientRepository)
    }

    @Provides
    fun provideMainPresenter(
        router: Router
    ): MainActivityPresenter {
        return MainActivityPresenter(router)
    }
}