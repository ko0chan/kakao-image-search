package com.kychan.kakaoimageapi.data

import androidx.paging.DataSource
import javax.inject.Inject

interface SearchRemoteDataSource {
    fun searchImage(searchWord: String): DataSource.Factory<Int, SearchImageDocumentsResponse>
}

class SearchRemoteDataSourceImpl @Inject constructor(private val kakaoApi: KakaoApi) : SearchRemoteDataSource {
    override fun searchImage(searchWord: String): DataSource.Factory<Int, SearchImageDocumentsResponse> {
        return SearchImageDataSourceFactory(kakaoApi, searchWord)
    }
}