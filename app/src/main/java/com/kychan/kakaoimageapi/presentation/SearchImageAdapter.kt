package com.kychan.kakaoimageapi.presentation

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

class SearchImageAdapter(
    private val itemClick: (SearchImageItem) -> Unit
) :
    PagedListAdapter<SearchImageItem, SearchImageViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageViewHolder =
        SearchImageViewHolder(parent, itemClick)

    override fun onBindViewHolder(holder: SearchImageViewHolder, position: Int) {
        val searchImageItem: SearchImageItem? = getItem(position)
        if (searchImageItem != null) {
            holder.bind(searchImageItem)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchImageItem>() {

            override fun areItemsTheSame(oldItem: SearchImageItem, newItem: SearchImageItem): Boolean =
                oldItem.imageUrl == newItem.imageUrl

            override fun areContentsTheSame(oldItem: SearchImageItem, newItem: SearchImageItem): Boolean =
                oldItem == newItem
        }
    }
}