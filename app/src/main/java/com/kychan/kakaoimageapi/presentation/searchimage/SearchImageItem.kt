package com.kychan.kakaoimageapi.presentation.searchimage

import com.kychan.kakaoimageapi.domain.entity.SearchImageDocuments
import java.io.Serializable
import java.text.SimpleDateFormat

data class SearchImageItem(
    val imageUrl: String,
    val displaySiteName: String,
    val datetime: String
) : Serializable {
    companion object {
        fun of(searchImageDocuments: SearchImageDocuments): SearchImageItem =
            SearchImageItem(
                imageUrl = searchImageDocuments.imageUrl,
                displaySiteName = searchImageDocuments.displaySitename,
                datetime = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(searchImageDocuments.datetime)
            )
    }
}