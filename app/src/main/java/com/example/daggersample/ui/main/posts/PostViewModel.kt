package com.example.daggersample.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.daggersample.SessionManager
import com.example.daggersample.models.Post
import com.example.daggersample.network.main.MainRepository
import com.example.daggersample.ui.main.Resource
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(
        private val sessionManager: SessionManager,
        private val mainRepository: MainRepository
        ) : ViewModel() {

    private var posts = MediatorLiveData<Resource<List<Post>>>()

    fun observePosts() : LiveData<Resource<List<Post>>>{
        posts.value = Resource.loading(null)
        //se possivel não usar dessa forma, ficaria legal passar o id pelo intent
        val source = LiveDataReactiveStreams.fromPublisher(
                mainRepository.getPostsFromUser(sessionManager.cachedUser.value?.data?.id).onErrorReturn {
                    val post = Post()
                    val posts = arrayListOf<Post>()
                    post.id = -1
                    posts.add(post)
                    return@onErrorReturn posts
                }.map {

                    if (it.isNotEmpty() && it[0].id == -1){
                        return@map Resource.error("Something went wrong", null)
                    }

                    return@map Resource.success(it)
                }.subscribeOn(Schedulers.io()))

        posts.addSource(source, Observer {
            posts.value = it as Resource<List<Post>>?
            posts.removeSource(source)
        })
        return posts
    }

    companion object{
        const val TAG = "PostViewModel"
    }
}