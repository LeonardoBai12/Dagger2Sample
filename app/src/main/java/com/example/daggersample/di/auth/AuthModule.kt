package com.example.daggersample.di.auth

import com.example.daggersample.network.auth.AuthRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {
    @Provides
    fun providesAuthApi(retrofit: Retrofit) : AuthRepository{
        return retrofit.create(AuthRepository::class.java)
    }
}