package com.kychan.kakaoimageapi.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class SearchImageResponse(
    @SerializedName("meta")
    val searchImageMeta: SearchImageMeta?,
    @SerializedName("documents")
    val searchImageDocuments: SearchImageDocuments?
)

data class SearchImageMeta(
    @SerializedName("total_count")
    val totalCount: Int = -1,
    @SerializedName("pageable_count")
    val pageableCount: Int = -1,
    @SerializedName("is_end")
    val isEnd: Boolean = false
)

data class SearchImageDocuments(
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
    val doc_url: String?,
    @SerializedName("datetime")
    val datetime: Date?
)