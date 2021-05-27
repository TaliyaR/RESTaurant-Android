package com.example.restaurant.di

import com.example.restaurant.data.network.ApiService
import com.example.restaurant.data.storage.DataPref
import com.example.restaurant.repositories.AuthRepository
import com.example.restaurant.repositories.ClientRepository
import com.example.restaurant.repositories.EmployeeRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(
    ): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideApiService(
        gson: Gson,
        httpClient: OkHttpClient.Builder
    ) = Retrofit.Builder()
        .client(httpClient.build())
        .baseUrl("https://restaurantteam404.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        dataPref: DataPref
    ): OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor(AuthInterceptor(dataPref))

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideClientRepository(apiService: ApiService, dataPref: DataPref): ClientRepository {
        return ClientRepository(apiService, dataPref)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(apiService: ApiService, dataPref: DataPref): AuthRepository {
        return AuthRepository(apiService, dataPref)
    }

    @Singleton
    @Provides
    fun provideEmployeeRepository(apiService: ApiService): EmployeeRepository {
        return EmployeeRepository(apiService)
    }
}