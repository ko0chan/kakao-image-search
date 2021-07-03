package com.kychan.kakaoimageapi.data

import io.reactivex.Single
import javax.inject.Inject

interface SearchRemoteDataSource {
    fun searchImage(searchWord: String): Single<SearchImageResponse>
}

class SearchRemoteDataSourceImpl @Inject constructor(private val kakaoApi: KakaoApi) : SearchRemoteDataSource {
    override fun searchImage(searchWord: String): Single<SearchImageResponse> {
        return kakaoApi.getSearchImage(query = searchWord)
    }
}