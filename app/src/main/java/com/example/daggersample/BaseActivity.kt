package com.example.daggersample

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.daggersample.ui.auth.AuthActivity
import com.example.daggersample.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    fun subscribeObservers(){
        sessionManager.cachedUser.observe(this, Observer {
            if( it.status == AuthResource.AuthStatus.NOT_AUTHENTICATED)
                navLoginScreen()
        })
    }
    
    fun navLoginScreen(){
        intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        const val TAG = "BaseActivity"
    }
}