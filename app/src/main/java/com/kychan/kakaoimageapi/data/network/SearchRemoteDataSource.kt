package com.kychan.kakaoimageapi.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import com.kychan.kakaoimageapi.data.SearchImagePagingSource
import io.reactivex.Observable
import javax.inject.Inject

interface SearchRemoteDataSource {
    fun searchImage(searchWord: String): Observable<PagingData<SearchImageDocumentsResponse>>
}

class SearchRemoteDataSourceImpl @Inject constructor(private val kakaoApi: KakaoApi) : SearchRemoteDataSource {
    override fun searchImage(searchWord: String): Observable<PagingData<SearchImageDocumentsResponse>> {
        return Pager(
            config = pagingConfig(),
            pagingSourceFactory = { SearchImagePagingSource(kakaoApi, searchWord) }
        ).observable
    }

    companion object {
        private const val PAGE_SIZE = 30

        fun pagingConfig() = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true,
            initialLoadSize = PAGE_SIZE,
            prefetchDistance = 3
        )
    }
}