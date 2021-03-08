package com.example.daggersample.di.main

import com.example.daggersample.network.auth.AuthRepository
import com.example.daggersample.network.main.MainRepository
import com.example.daggersample.ui.main.posts.PostAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun providesPostAdapter() : PostAdapter{
        return PostAdapter()
    }

    @Provides
    fun providesPostApi(retrofit: Retrofit) : MainRepository {
        return retrofit.create(MainRepository::class.java)
    }
}