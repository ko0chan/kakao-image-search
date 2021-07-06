package com.kychan.kakaoimageapi.domain.entity

import java.util.*

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