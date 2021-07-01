package com.kychan.kakaoimageapi.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kychan.kakaoimageapi.R
import com.kychan.kakaoimageapi.databinding.ActivityFullImageBinding

class FullImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    companion object {
        private const val KEY_IMAGE_INFO = "IMAGE_INFO"
        fun getIntent(context: Context, searchImageItem: SearchImageItem) =
            Intent(context, FullImageActivity::class.java)
                .putExtra(KEY_IMAGE_INFO, searchImageItem)
    }
}