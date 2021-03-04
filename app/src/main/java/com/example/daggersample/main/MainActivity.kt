package com.example.daggersample.main

import android.os.Bundle
import com.example.daggersample.BaseActivity
import com.example.daggersample.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    companion object{
        const val TAG = "MainActivity"
    }

}