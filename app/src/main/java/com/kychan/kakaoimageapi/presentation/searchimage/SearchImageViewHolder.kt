package com.kychan.kakaoimageapi.presentation.searchimage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kychan.kakaoimageapi.R
import com.kychan.kakaoimageapi.databinding.ItemSearchListBinding

class SearchImageViewHolder(
    parent: ViewGroup,
    private val itemClick: (SearchImageItem, View) -> Unit,
    private val binding: ItemSearchListBinding =
        ItemSearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchImageItem) {
        with(binding) {
            if (item.imageUrl.isEmpty()) {
                movieImage.setImageResource(R.drawable.ic_cancel)
            } else {
                Glide.with(itemView.context)
                    .load(item.imageUrl)
                    .placeholder(R.color.theme_F5F6F6)
                    .into(movieImage)
            }

            movieImage.setOnClickListener {
                itemClick(item, it)
            }
        }
    }
}