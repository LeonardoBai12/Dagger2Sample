package com.example.daggersample.network.main

import com.example.daggersample.models.Post
import com.example.daggersample.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainRepository {
    @GET
    fun getPostsFromUser( @Query("userId") userId : Int) : Flowable<List<Post>>

}