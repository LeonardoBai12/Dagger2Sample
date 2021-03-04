package com.example.daggersample.network.auth

import com.example.daggersample.models.User
import com.example.daggersample.util.Constants
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET(Constants.FILTER)
    fun getUser( @Path("id") id : Int) : Flowable<User>
}