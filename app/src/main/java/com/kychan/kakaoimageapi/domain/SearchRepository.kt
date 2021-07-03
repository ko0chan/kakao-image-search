package com.kychan.kakaoimageapi.domain

import com.kychan.kakaoimageapi.data.SearchImageResponse
import io.reactivex.Single

interface SearchRepository {
    fun searchImage(searchWord: String): Single<SearchImageResponse>
}