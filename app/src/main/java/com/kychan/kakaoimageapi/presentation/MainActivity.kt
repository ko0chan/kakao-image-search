package com.kychan.kakaoimageapi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.kychan.kakaoimageapi.R
import com.kychan.kakaoimageapi.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val searchImageAdapter by lazy {
        SearchImageAdapter {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setView()
    }

    private fun setView() {
        with(binding) {
            rvImage.adapter = searchImageAdapter
            rvImage.layoutManager = GridLayoutManager(this@MainActivity, 3)
            searchImageAdapter.submitList(
                listOf(
                    SearchImageItem(
                        "data:image/png;base64,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",
                        "1",
                        Date()
                    ),
                    SearchImageItem(
                        "data:image/png;base64,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",
                        "1",
                        Date()
                    ),
                    SearchImageItem(
                        "https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_default_2x_r2.png",
                        "1",
                        Date()
                    ),
                    SearchImageItem(
                        "https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_default_2x_r2.png",
                        "1",
                        Date()
                    ),
                    SearchImageItem(
                        "https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_default_2x_r2.png",
                        "1",
                        Date()
                    ),
                    SearchImageItem(
                        "https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_default_2x_r2.png",
                        "1",
                        Date()
                    ),
                    SearchImageItem(
                        "https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_default_2x_r2.png",
                        "1",
                        Date()
                    )
                )
            )

        }
    }
}