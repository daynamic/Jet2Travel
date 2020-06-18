package com.akshat.jet2sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshat.jet2sample.R
import com.akshat.jet2sample.data.State
import com.akshat.jet2sample.data.State.ERROR
import com.akshat.jet2sample.data.State.LOADING
import kotlinx.android.synthetic.main.item_list_footer.view.*

/**
 * Created by Akshat on 17/06/20.
 */
class ListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(status: State?) {
        itemView.progress_bar.visibility = if (status == LOADING) View.VISIBLE else View.INVISIBLE
        itemView.txt_error.visibility = if (status == ERROR) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_footer, parent, false)
            view.txt_error.setOnClickListener { retry() }
            return ListFooterViewHolder(view)
        }
    }


}