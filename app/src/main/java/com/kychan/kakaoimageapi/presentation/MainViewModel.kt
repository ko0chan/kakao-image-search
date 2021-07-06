package com.kychan.kakaoimageapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.kychan.kakaoimageapi.data.SearchImageDataSourceFactory
import com.kychan.kakaoimageapi.domain.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _searchImageList = MutableLiveData<PagedList<SearchImageItem>>()
    val searchImageList: LiveData<PagedList<SearchImageItem>>
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
            RxPagedListBuilder(
                searchRepository.searchImage(searchWord)
                    .map {
                        SearchImageItem.of(it)
                    },
                SearchImageDataSourceFactory.pagedListConfig()
            ).buildObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ pagedList ->
                    _searchImageList.value = pagedList
                }, {

                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}