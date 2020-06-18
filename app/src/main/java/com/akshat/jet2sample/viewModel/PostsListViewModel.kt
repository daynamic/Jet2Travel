package com.akshat.jet2sample.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.akshat.jet2sample.data.NetworkService
import com.akshat.jet2sample.data.PostsDataSource
import com.akshat.jet2sample.data.PostsDataSourceFactory
import com.akshat.jet2sample.data.State
import com.akshat.jet2sample.model.Response
import com.akshat.jet2sample.model.ResponseItem
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Akshat on 17/06/20.
 */
class PostsListViewModel : ViewModel(){
    private val networkService = NetworkService.getService()
     var newsList: LiveData<PagedList<ResponseItem>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 1
    private val postsDataSourceFactory: PostsDataSourceFactory

    init {
        postsDataSourceFactory = PostsDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2 *10)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder(postsDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap(
        postsDataSourceFactory.postsDataSourceLiveData,
        PostsDataSource::state
    )

    fun retry() {
        postsDataSourceFactory.postsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}