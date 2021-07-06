package com.kychan.kakaoimageapi.presentation.searchimage

import com.kychan.kakaoimageapi.domain.entity.SearchImageDocuments
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