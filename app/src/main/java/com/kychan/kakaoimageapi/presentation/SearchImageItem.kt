package com.kychan.kakaoimageapi.presentation

import java.io.Serializable
import java.util.*

data class SearchImageItem(
    val imageUrl: String,
    val displaySiteName: String,
    val datetime: Date
) : Serializable