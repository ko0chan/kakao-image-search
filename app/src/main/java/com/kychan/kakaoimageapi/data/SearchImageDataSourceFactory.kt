package com.kychan.kakaoimageapi.data

import androidx.paging.DataSource
import androidx.paging.PagedList

class SearchImageDataSourceFactory(
    private val kakaoApi: KakaoApi,
    private val keyword: String
) : DataSource.Factory<Int, SearchImageDocumentsResponse>() {

    override fun create(): DataSource<Int, SearchImageDocumentsResponse> {
        return SearchImageDataSource(kakaoApi, keyword)
    }

    companion object {
        private const val PAGE_SIZE = 30

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}