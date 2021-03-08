package com.example.daggersample.ui.main.posts

import androidx.lifecycle.ViewModel
import com.example.daggersample.SessionManager
import com.example.daggersample.network.main.MainRepository
import javax.inject.Inject

class PostViewModel @Inject constructor(
        private val sessionManager: SessionManager,
        private val mainRepository: MainRepository
        ) : ViewModel() {



    companion object{
        const val TAG = "PostViewModel"
    }
}