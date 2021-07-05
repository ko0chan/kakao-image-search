package com.kychan.kakaoimageapi.domain

import java.util.*

data class SearchImage(
    val searchImageMeta: SearchImageMeta,
    val searchImageDocumentsList: List<SearchImageDocuments>
)

data class SearchImageMeta(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)

data class SearchImageDocuments(
    val collection: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val displaySitename: String,
    val docUrl: String,
    val datetime: Date
)