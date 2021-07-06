package com.kychan.kakaoimageapi.domain.usecase

import androidx.paging.PagingData
import com.kychan.kakaoimageapi.domain.entity.SearchImageDocuments
import com.kychan.kakaoimageapi.domain.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetSearchImage @Inject constructor(private val searchRepository: SearchRepository) {
    operator fun invoke(searchWord: String): Observable<PagingData<SearchImageDocuments>> {
        return searchRepository.searchImage(searchWord)
    }
}