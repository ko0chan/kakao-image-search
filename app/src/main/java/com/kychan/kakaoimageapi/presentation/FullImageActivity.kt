package com.kychan.kakaoimageapi.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kychan.kakaoimageapi.databinding.ActivityFullImageBinding
import java.text.SimpleDateFormat

class FullImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullImageBinding
    private val searchImageItem by lazy {
        intent.getSerializableExtra(KEY_IMAGE_INFO) as SearchImageItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
    }

    private fun setView() {
        with(binding) {
            Glide.with(this@FullImageActivity)
                .load(searchImageItem.imageUrl)
                .into(fullImage)

            displaySitename.text = searchImageItem.displaySiteName
            datetime.text = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(searchImageItem.datetime)
        }
    }

    companion object {
        private const val KEY_IMAGE_INFO = "IMAGE_INFO"
        fun getIntent(context: Context, searchImageItem: SearchImageItem) =
            Intent(context, FullImageActivity::class.java)
                .putExtra(KEY_IMAGE_INFO, searchImageItem)
    }
}