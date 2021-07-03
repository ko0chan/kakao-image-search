package com.kychan.kakaoimageapi.data

import com.kychan.kakaoimageapi.domain.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) :
    SearchRepository {
    override fun searchImage(searchWord: String): Single<SearchImageResponse> {
        return searchRemoteDataSource.searchImage(searchWord = searchWord)
    }
}