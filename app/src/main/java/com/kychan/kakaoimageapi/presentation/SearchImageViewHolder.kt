package com.kychan.kakaoimageapi.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kychan.kakaoimageapi.R
import com.kychan.kakaoimageapi.databinding.ItemSearchListBinding

class SearchImageViewHolder(
    parent: ViewGroup,
    private val itemClick: (SearchImageItem) -> Unit,
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
                    .into(movieImage)
            }

            movieImage.setOnClickListener {
                itemClick(item)
            }
        }
    }
}