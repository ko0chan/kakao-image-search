package com.kychan.kakaoimageapi.domain

import androidx.paging.DataSource

interface SearchRepository {
    fun searchImage(searchWord: String): DataSource.Factory<Int, SearchImageDocuments>
}