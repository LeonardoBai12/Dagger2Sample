package com.example.daggersample.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.daggersample.BaseActivity
import com.example.daggersample.R
import com.example.daggersample.ui.main.posts.PostFragment
import com.example.daggersample.ui.main.profile.ProfileFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testFragment();
    }

    fun testFragment(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, PostFragment())
                .commit();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout) {
            sessionManager.logout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        const val TAG = "MainActivity"
    }

}