package com.example.daggersample.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.daggersample.R
import com.example.daggersample.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance() : Retrofit{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideRequestOptions() : RequestOptions{
        return RequestOptions().placeholder(R.drawable.white_background)
            .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager{
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun providesAppDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.logo) as Drawable
    }

}