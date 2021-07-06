package com.kychan.kakaoimageapi.domain

import androidx.paging.PagingData
import io.reactivex.Observable

interface SearchRepository {
    fun searchImage(searchWord: String): Observable<PagingData<SearchImageDocuments>>
}