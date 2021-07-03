package com.kychan.kakaoimageapi.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kychan.kakaoimageapi.di.ApiModule
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _searchImageList = MutableLiveData<List<SearchImageItem>>()
    val searchImageList: LiveData<List<SearchImageItem>>
        get() = _searchImageList

    val textChange: PublishSubject<String> = PublishSubject.create()

    init {
        compositeDisposable.add(
            textChange
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    getSearchImage(it)
                }
                .subscribe()
        )
    }


    fun getSearchImage(searchWord: String) {
        compositeDisposable.add(
            ApiModule.provideKakaoApi()
                .getSearchImage(query = searchWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _searchImageList.value = it.toSearchImageListItem()
                }, {
                    Log.d("TAG", it.toString())
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}