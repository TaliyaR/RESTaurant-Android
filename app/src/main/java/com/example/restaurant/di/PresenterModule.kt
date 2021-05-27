package com.example.restaurant.di

import com.example.restaurant.presenter.current.CurrentFragmentPresenter
import com.example.restaurant.presenter.employeeProfile.EmployeeProfilePresenter
import com.example.restaurant.presenter.login.LoginActivityPresenter
import com.example.restaurant.presenter.main.MainActivityPresenter
import com.example.restaurant.presenter.mainCook.MainCookPresenter
import com.example.restaurant.presenter.qr.QrActivityPresenter
import com.example.restaurant.presenter.splash.SplashPresenter
import com.example.restaurant.repositories.AuthRepository
import com.example.restaurant.repositories.ClientRepository
import com.example.restaurant.repositories.EmployeeRepository
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

    @Provides
    fun provideSplashPresenter(
        clientRepository: ClientRepository
    ): SplashPresenter {
        return SplashPresenter(clientRepository)
    }

    @Provides
    fun provideLoginPresenter(
        authRepository: AuthRepository
    ): LoginActivityPresenter {
        return LoginActivityPresenter(authRepository)
    }

    @Provides
    fun provideMainCookPresenter(
        router: Router
    ): MainCookPresenter {
        return MainCookPresenter(router)
    }

    @Provides
    fun provideEmployeeProfilePresenter(
        employeeRepository: EmployeeRepository
    ): EmployeeProfilePresenter {
        return EmployeeProfilePresenter(employeeRepository)
    }
}