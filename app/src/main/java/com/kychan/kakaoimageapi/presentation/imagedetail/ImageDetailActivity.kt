package com.kychan.kakaoimageapi.presentation.imagedetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kychan.kakaoimageapi.databinding.ActivityImageDetailBinding
import com.kychan.kakaoimageapi.presentation.searchimage.SearchImageItem
import java.text.SimpleDateFormat

class ImageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailBinding
    private val searchImageItem by lazy {
        intent.getSerializableExtra(KEY_IMAGE_INFO) as SearchImageItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
    }

    private fun setView() {
        with(binding) {
            Glide.with(this@ImageDetailActivity)
                .load(searchImageItem.imageUrl)
                .into(fullImage)

            displaySitename.text = searchImageItem.displaySiteName
            datetime.text = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(searchImageItem.datetime)
        }
    }

    companion object {
        private const val KEY_IMAGE_INFO = "IMAGE_INFO"
        fun getIntent(context: Context, searchImageItem: SearchImageItem) =
            Intent(context, ImageDetailActivity::class.java)
                .putExtra(KEY_IMAGE_INFO, searchImageItem)
    }
}