package com.kychan.kakaoimageapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kychan.kakaoimageapi.di.ApiModule
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _searchImageList = MutableLiveData<List<SearchImageItem>>()
    val searchImageList: LiveData<List<SearchImageItem>>
        get() = _searchImageList


    fun getSearchImage(searchWord: String) {
        compositeDisposable.add(
            ApiModule.provideKakaoApi()
                .getSearchImage(query = searchWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _searchImageList.value = it.toSearchImageListItem()
                }, {

                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}