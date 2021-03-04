package com.example.daggersample.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.IntegerRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.bumptech.glide.RequestManager
import com.example.daggersample.R
import com.example.daggersample.main.MainActivity
import com.example.daggersample.models.User
import com.example.daggersample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private lateinit var userId : EditText

    @Inject
    lateinit var logo : Drawable

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val authViewModel: AuthViewModel by viewModels {
        viewModelFactory
    }

    @Inject
    lateinit var requestManager: RequestManager
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        userId = findViewById(R.id.user_id_input)
        progressBar = findViewById(R.id.progress_bar)

        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        setLogo()

        subscribeObservers()
    }

    fun setLogo(){
        requestManager.load(logo).into(findViewById(R.id.login_logo))
    }

    override fun onClick(v: View?) {
        if (v != null && v.id ==  R.id.login_button)
            attempLogin()
    }

    fun subscribeObservers(){
        authViewModel.observeAuthState().observe(this, this::update)
    }

    fun update(userAuthResource: AuthResource<User>){
        if( userAuthResource.status == AuthResource.AuthStatus.LOADING)
            showProgressBar(true)
        else if( userAuthResource.status == AuthResource.AuthStatus.AUTHENTICATED) {
            showProgressBar(false)
            onLoginSuccess()
        }else if( userAuthResource.status == AuthResource.AuthStatus.NOT_AUTHENTICATED)
            showProgressBar(false)
        else //ERROR
            showProgressBar(false)

    }

    fun showProgressBar(visible : Boolean){
        if( visible )
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.GONE

    }

    private fun attempLogin() {
        if(TextUtils.isEmpty(userId.text.toString()))
            return
        authViewModel.authenticateWithId(Integer.parseInt(userId.text.toString()))
    }

    private fun onLoginSuccess(){
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}