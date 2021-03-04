package com.example.daggersample.ui.auth

import androidx.lifecycle.*
import com.example.daggersample.SessionManager
import com.example.daggersample.models.User
import com.example.daggersample.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val authApi: AuthApi,
                                        private val sessionManager: SessionManager) : ViewModel() {

    fun authenticateWithId(userId: Int) {
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    fun queryUserId( id : Int) : LiveData<AuthResource<User>>{
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(id)
                        .onErrorReturn {
                            val errorUser: User = User()
                            errorUser.id = -1
                            return@onErrorReturn errorUser
                        }
                        .map {
                            if (it.id == -1) {
                                return@map AuthResource.error("Could not authenticate", it)
                            }
                            return@map AuthResource.authenticated(it)
                        }
                        .subscribeOn(Schedulers.io())
        )
    }

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.cachedUser

    }

}