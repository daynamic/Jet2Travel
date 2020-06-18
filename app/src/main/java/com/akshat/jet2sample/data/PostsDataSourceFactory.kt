package com.akshat.jet2sample.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.akshat.jet2sample.model.Response
import com.akshat.jet2sample.model.ResponseItem
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Akshat on 17/06/20.
 */
class PostsDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService):
    DataSource.Factory<Int, ResponseItem>(){
    val postsDataSourceLiveData = MutableLiveData<PostsDataSource>()

    override fun create(): DataSource<Int, ResponseItem> {
        val postsDataSource = PostsDataSource(networkService, compositeDisposable)
        postsDataSourceLiveData.postValue(postsDataSource)
        return postsDataSource
    }
}