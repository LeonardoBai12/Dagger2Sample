package com.example.daggersample.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.bumptech.glide.RequestManager
import com.example.daggersample.R
import com.example.daggersample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    //lateinit var viewModel : AuthViewModel

    @Inject
    lateinit var logo : Drawable

    //@Inject
    //lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)

        setLogo()
    }

    fun setLogo(){
        requestManager.load(logo).into(findViewById(R.id.login_logo))
    }

}