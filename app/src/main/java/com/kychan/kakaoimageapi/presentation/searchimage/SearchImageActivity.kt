package com.kychan.kakaoimageapi.presentation.searchimage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.kychan.kakaoimageapi.databinding.ActivitySearchImageBinding
import com.kychan.kakaoimageapi.presentation.imagedetail.ImageDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchImageBinding
    private val mainViewModel by viewModels<SearchImageViewModel>()
    private val searchImageAdapter by lazy {
        SearchImageAdapter { searchImageItem, view ->
            val options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this@SearchImageActivity, view, view.transitionName)

            startActivity(ImageDetailActivity.getIntent(this, searchImageItem), options.toBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setViewModel()
    }

    private fun setView() {
        with(binding) {
            searchView.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        if (text.isNotEmpty()) {
                            mainViewModel.fetchSearchImage(text.toString())
                        }
                        val inputMethodManager =
                            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(searchView.windowToken, 0)
                        return@setOnEditorActionListener true
                    }
                    false
                }
                doOnTextChanged { text, _, _, _ ->
                    clearButton.isGone = text.isNullOrEmpty()
                    mainViewModel.textChange.onNext(text.toString())
                }
            }

            clearButton.setOnClickListener {
                searchView.text.clear()
                searchView.requestFocus()
            }

            rvImage.adapter = searchImageAdapter
            rvImage.layoutManager = GridLayoutManager(this@SearchImageActivity, 3)

            searchImageAdapter.addLoadStateListener {
                binding.emptyContainer.isVisible = searchImageAdapter.itemCount == 0
            }

        }
    }

    private fun setViewModel() {
        with(mainViewModel) {
            searchImageList.observe(this@SearchImageActivity, {
                searchImageAdapter.submitData(lifecycle, it)
            })
        }
    }
}