package com.kychan.kakaoimageapi.data

import androidx.paging.PagingData
import androidx.paging.map
import com.kychan.kakaoimageapi.data.network.SearchRemoteDataSource
import com.kychan.kakaoimageapi.domain.entity.SearchImageDocuments
import com.kychan.kakaoimageapi.domain.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) :
    SearchRepository {
    override fun searchImage(searchWord: String): Observable<PagingData<SearchImageDocuments>> {
        return searchRemoteDataSource.searchImage(searchWord)
            .map { pagingData ->
                pagingData.map {
                    it.toSearchImageDocuments()
                }
            }
    }
}