package com.example.daggersample

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.example.daggersample.models.User
import com.example.daggersample.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class SessionManager(val cachedUser : MediatorLiveData<AuthResource<User>>) {

    @Inject
    constructor() : this(
            MediatorLiveData()
    )

    fun authenticateWithId( source : LiveData<AuthResource<User>>){
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source, Observer {
            cachedUser.value = it
            cachedUser.removeSource(source);
        })
    }

    fun logout(){
        cachedUser.value = AuthResource.logout()
    }

    companion object{
        const val TAG : String = "SessionManager"
    }

}