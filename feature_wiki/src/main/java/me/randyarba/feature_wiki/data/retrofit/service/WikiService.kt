package me.randyarba.feature_wiki.data.retrofit.service

import com.igorwojda.showcase.feature.album.data.retrofit.response.SearchWikiResponse
import com.igorwojda.showcase.feature.album.data.retrofit.response.WikiInfoResponse
import retrofit2.http.POST
import retrofit2.http.Query

internal interface WikiService {
    @POST("./?method=album.search")
    suspend fun searchWikiAsync(
        @Query("album") phrase: String,
        @Query("limit") limit: Int = 60
    ): SearchWikiResponse

    @POST("./?method=album.getInfo")
    suspend fun getWikiInfoAsync(
        @Query("artist") artistName: String,
        @Query("album") albumName: String,
        @Query("mbid") mbId: String?
    ): WikiInfoResponse?
}