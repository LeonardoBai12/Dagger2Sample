package com.example.daggersample.di

import androidx.lifecycle.ViewModelProvider
import com.example.daggersample.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory( providerFactory : ViewModelProviderFactory) : ViewModelProvider.Factory


}