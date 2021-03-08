package com.example.daggersample.di

import com.example.daggersample.di.auth.AuthModule
import com.example.daggersample.di.auth.AuthViewModelModule
import com.example.daggersample.di.main.MainFragmentBuildersModule
import com.example.daggersample.di.main.MainModule
import com.example.daggersample.di.main.MainViewModelModule
import com.example.daggersample.ui.main.MainActivity
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

    @ContributesAndroidInjector(
            modules =  [
                MainFragmentBuildersModule::class,
                MainViewModelModule::class,
                MainModule::class
            ]
    )
    abstract fun contributeMainActivity() : MainActivity

}