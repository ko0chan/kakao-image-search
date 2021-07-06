package com.kychan.kakaoimageapi.presentation.searchimage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.kychan.kakaoimageapi.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _searchImageList = MutableLiveData<PagingData<SearchImageItem>>()
    val searchImageList: LiveData<PagingData<SearchImageItem>>
        get() = _searchImageList

    val textChange: PublishSubject<String> = PublishSubject.create()

    init {
        compositeDisposable.add(
            textChange
                .debounce(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getSearchImage(it)
                }, {

                })
        )
    }

    fun getSearchImage(searchWord: String) {
        compositeDisposable.add(
            searchRepository.searchImage(searchWord)
                .subscribe({ pagingData ->
                    _searchImageList.value = pagingData.map {
                        SearchImageItem.of(it)
                    }
                }, {

                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}