package com.example.daggersample.network.main

import com.example.daggersample.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainRepository {
    @GET("posts")
    fun getPostsFromUser( @Query("userId") userId: Int?) : Flowable<List<Post>>

}