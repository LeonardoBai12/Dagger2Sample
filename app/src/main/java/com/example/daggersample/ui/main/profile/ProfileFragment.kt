package com.example.daggersample.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.daggersample.R
import com.example.daggersample.SessionManager
import com.example.daggersample.models.User
import com.example.daggersample.ui.auth.AuthResource
import com.example.daggersample.ui.auth.AuthViewModel
import dagger.android.support.DaggerFragment
import java.lang.reflect.Constructor
import javax.inject.Inject

class ProfileFragment : DaggerFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var email : TextView
    private lateinit var website : TextView
    private lateinit var username : TextView

    private val profileViewModel: ProfileViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeObservers()
        username = view.findViewById(R.id.username)
        email = view.findViewById(R.id.email)
        website = view.findViewById(R.id.website)
    }

    fun subscribeObservers(){
        profileViewModel.observeAuthState().removeObservers(viewLifecycleOwner)
        profileViewModel.observeAuthState().observe(viewLifecycleOwner, this::update)
    }

    fun update(userAuthResource: AuthResource<User>){
        if(userAuthResource.status == AuthResource.AuthStatus.AUTHENTICATED ){
            setUserDetails(userAuthResource.data)
        } else if (userAuthResource.status == AuthResource.AuthStatus.ERROR ) {
            setErrorDetails(userAuthResource.message)
        }

    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = getString(R.string.error)
        website.text = getString(R.string.error)
    }

    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }

    companion object{
        const val TAG = "ProfileViewModel"
    }

}