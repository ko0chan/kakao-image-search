package com.kychan.kakaoimageapi.domain.repository

import androidx.paging.PagingData
import com.kychan.kakaoimageapi.domain.entity.SearchImageDocuments
import io.reactivex.Observable

interface SearchRepository {
    fun searchImage(searchWord: String): Observable<PagingData<SearchImageDocuments>>
}