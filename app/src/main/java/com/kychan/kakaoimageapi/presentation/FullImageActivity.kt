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
        fun getIntent(context: Context) =
            Intent(context, FullImageActivity::class.java)
    }
}