package com.example.daggersample.di

import com.example.daggersample.di.auth.AuthModule
import com.example.daggersample.di.auth.AuthViewModelModule
import com.example.daggersample.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            AuthViewModelModule::class,
            AuthModule::class
        ]
    )
    abstract fun contributeAuthActivity() : AuthActivity

}