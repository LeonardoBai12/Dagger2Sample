package com.example.daggersample.ui.auth

import androidx.lifecycle.ViewModel
import com.example.daggersample.network.auth.AuthApi
import javax.inject.Inject
import javax.inject.Provider

class AuthViewModel @Inject constructor(private val authApi : AuthApi) : ViewModel() {

    fun a(){
       // authApi.getFakeStuff()
    }
}