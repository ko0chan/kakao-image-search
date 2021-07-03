package com.kychan.kakaoimageapi.data

import com.google.gson.annotations.SerializedName
import com.kychan.kakaoimageapi.presentation.SearchImageItem
import java.util.*

data class SearchImageResponse(
    @SerializedName("meta")
    val searchImageMetaResponse: SearchImageMetaResponse?,
    @SerializedName("documents")
    val searchImageDocumentsResponseList: List<SearchImageDocumentsResponse>?
) {
    fun toSearchImageListItem(): List<SearchImageItem> {
        val list = mutableListOf<SearchImageItem>()
        if (searchImageDocumentsResponseList != null) {
            for (searchImageDocuments in searchImageDocumentsResponseList) {
                list.add(
                    SearchImageItem(
                        imageUrl = searchImageDocuments.imageUrl.orEmpty(),
                        displaySiteName = searchImageDocuments.displaySitename.orEmpty(),
                        datetime = searchImageDocuments.datetime ?: Date()
                    )
                )
            }
        }
        return list
    }
}

data class SearchImageMetaResponse(
    @SerializedName("total_count")
    val totalCount: Int = -1,
    @SerializedName("pageable_count")
    val pageableCount: Int = -1,
    @SerializedName("is_end")
    val isEnd: Boolean = false
)

data class SearchImageDocumentsResponse(
    @SerializedName("collection")
    val collection: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("width")
    val width: Int = -1,
    @SerializedName("height")
    val height: Int = -1,
    @SerializedName("display_sitename")
    val displaySitename: String?,
    @SerializedName("doc_url")
    val docUrl: String?,
    @SerializedName("datetime")
    val datetime: Date?
)