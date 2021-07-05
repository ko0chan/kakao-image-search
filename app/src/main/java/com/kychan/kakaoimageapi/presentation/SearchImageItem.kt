package com.kychan.kakaoimageapi.presentation

import com.kychan.kakaoimageapi.domain.SearchImageDocuments
import java.io.Serializable
import java.util.*

data class SearchImageItem(
    val imageUrl: String,
    val displaySiteName: String,
    val datetime: Date
) : Serializable {
    companion object {
        fun of(searchImageDocuments: SearchImageDocuments): SearchImageItem =
            SearchImageItem(
                imageUrl = searchImageDocuments.imageUrl,
                displaySiteName = searchImageDocuments.displaySitename,
                datetime = searchImageDocuments.datetime
            )
    }
}