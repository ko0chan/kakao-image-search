package com.kychan.kakaoimageapi.data

import androidx.paging.PageKeyedDataSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchImageDataSource(
    private val kakaoApi: KakaoApi,
    private val query: String,
) : PageKeyedDataSource<Int, SearchImageDocumentsResponse>() {

    private fun getSearchImageList(page: Int, size: Int): Single<SearchImageResponse> {
        return kakaoApi.getSearchImage(
            query = query,
            page = page,
            size = size
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, SearchImageDocumentsResponse>) {
        var disposable: Disposable? = null
        val loadInitPage = 1
        disposable = getSearchImageList(loadInitPage, params.requestedLoadSize)
            .subscribe({
                callback.onResult(it.searchImageDocumentsResponseList.orEmpty(), null, loadInitPage + 1)
                disposable?.dispose()
            }, {
                disposable?.dispose()
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SearchImageDocumentsResponse>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SearchImageDocumentsResponse>) {
        var disposable: Disposable? = null
        disposable = getSearchImageList(params.key, params.requestedLoadSize)
            .subscribe({
                if (it.searchImageMetaResponse?.isEnd == true) {
                    callback.onResult(it.searchImageDocumentsResponseList.orEmpty(), null)
                } else {
                    callback.onResult(it.searchImageDocumentsResponseList.orEmpty(), params.key + 1)
                }
                disposable?.dispose()
            }, {
                disposable?.dispose()
            })
    }
}