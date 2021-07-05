package com.kychan.kakaoimageapi.data

import androidx.paging.DataSource
import com.kychan.kakaoimageapi.domain.SearchImageDocuments
import com.kychan.kakaoimageapi.domain.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) :
    SearchRepository {
    override fun searchImage(searchWord: String): DataSource.Factory<Int, SearchImageDocuments> {
        return searchRemoteDataSource.searchImage(searchWord)
            .map {
                it.toSearchImageDocuments()
            }
    }
}