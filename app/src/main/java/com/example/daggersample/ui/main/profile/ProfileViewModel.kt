package com.example.daggersample.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daggersample.SessionManager
import com.example.daggersample.models.User
import com.example.daggersample.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor( private val sessionManager: SessionManager  ) : ViewModel() {

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.cachedUser
    }

    companion object{
        const val TAG = "ProfileViewModel"
    }

}