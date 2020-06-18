package com.akshat.jet2sample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.akshat.jet2sample.R
import com.akshat.jet2sample.adapter.PostsListAdapter
import com.akshat.jet2sample.data.State
import com.akshat.jet2sample.viewModel.PostsListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PostsListViewModel
    private lateinit var postsListAdapter: PostsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(PostsListViewModel::class.java)
        initAdapter()
        initState()

    }

    private fun initAdapter() {
        postsListAdapter = PostsListAdapter { viewModel.retry() }
        recycler_view.adapter = postsListAdapter
        viewModel.newsList.observe(this,
            Observer {
                postsListAdapter.submitList(it)
            })
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                postsListAdapter.setState(state ?: State.DONE)
            }
        })
    }
}