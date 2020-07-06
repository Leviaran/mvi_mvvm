package me.randyarba.feature_wiki.domain.repository

import me.randyarba.feature_wiki.domain.model.WikiDomainModel

internal interface WikiRepository {

    suspend fun getWikiInfo(artistName: String, albumName: String, mbId: String?): WikiDomainModel?

    suspend fun searchWiki(phrase: String): List<WikiDomainModel>
}