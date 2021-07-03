package com.kychan.kakaoimageapi.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApi {

    @GET("v2/search/image")
    fun getSearchImage(
        @Header("Authorization") authorization: String = "KakaoAK 8f68bda18482639d9a484b48cce93cc9",
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): Single<SearchImageResponse>
}