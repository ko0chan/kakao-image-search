package com.kychan.kakaoimageapi.data

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.kychan.kakaoimageapi.data.network.KakaoApi
import com.kychan.kakaoimageapi.data.network.SearchImageDocumentsResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

class SearchImagePagingSource(
    private val kakaoApi: KakaoApi,
    private val query: String,
) : RxPagingSource<Int, SearchImageDocumentsResponse>() {

    override fun getRefreshKey(state: PagingState<Int, SearchImageDocumentsResponse>): Int? {
        return KEY_PAGE
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, SearchImageDocumentsResponse>> {
        val page = params.key ?: 1

        Log.d("SearchImageDataSource", "$page ${params.loadSize}")

        return kakaoApi.getSearchImage(query = query, page = page, size = params.loadSize)
            .subscribeOn(Schedulers.io())
            .map<LoadResult<Int, SearchImageDocumentsResponse>> { result ->
                LoadResult.Page(
                    data = result.searchImageDocumentsResponseList.orEmpty(),
                    prevKey = null,
                    nextKey = if (result.searchImageMetaResponse?.isEnd == true) null else page + 1
                )
            }
            .onErrorReturn { e ->
                when (e) {
                    is IOException -> LoadResult.Error(e)
                    is HttpException -> LoadResult.Error(e)
                    else -> throw e
                }
            }
    }

    companion object {
        private const val KEY_PAGE = 1
    }
}