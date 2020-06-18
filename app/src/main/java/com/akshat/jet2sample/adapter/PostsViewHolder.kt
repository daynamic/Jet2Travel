package com.akshat.jet2sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshat.jet2sample.R
import com.akshat.jet2sample.model.Response
import com.akshat.jet2sample.model.ResponseItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_posts.view.*

/**
 * Created by Akshat on 17/06/20.
 */
class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(responseItem: ResponseItem?) {
        if (responseItem != null) {
            itemView.txt_news_name.text = responseItem.id
            if (!responseItem.media[0].image.isNullOrEmpty())
                Picasso.get().load(responseItem.media[0].image).into(itemView.img_news_banner)
        }
    }

    companion object {
        fun create(parent: ViewGroup): PostsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_posts, parent, false)
            return PostsViewHolder(view)
        }
    }
}