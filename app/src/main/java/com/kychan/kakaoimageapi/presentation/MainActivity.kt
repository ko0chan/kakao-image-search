package com.kychan.kakaoimageapi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.kychan.kakaoimageapi.R
import com.kychan.kakaoimageapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private val searchImageAdapter by lazy {
        SearchImageAdapter {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setView()
        setViewModel()
    }

    private fun setView() {
        with(binding) {
            searchView.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        if (text.isNotEmpty()) {
                            mainViewModel.getSearchImage(text.toString())
                        }
                        return@setOnEditorActionListener true
                    }
                    false
                }
                doOnTextChanged { text, _, _, _ ->
                    clearButton.isGone = text.isNullOrEmpty()
                }
            }

            clearButton.setOnClickListener {
                searchView.text.clear()
                searchView.requestFocus()
            }

            rvImage.adapter = searchImageAdapter
            rvImage.layoutManager = GridLayoutManager(this@MainActivity, 3)


        }
    }

    private fun setViewModel() {
        with(mainViewModel) {
            searchImageList.observe(this@MainActivity, {
                searchImageAdapter.submitList(it)
            })
        }
    }
}