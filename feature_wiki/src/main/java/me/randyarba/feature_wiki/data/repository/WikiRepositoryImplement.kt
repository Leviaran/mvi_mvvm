package me.randyarba.feature_wiki.data.repository

import com.igorwojda.showcase.feature.album.data.model.toDomainModel
import me.randyarba.feature_wiki.data.retrofit.service.WikiService
import me.randyarba.feature_wiki.domain.repository.WikiRepository

internal class WikiRepositoryImplement(
    private val wikiService: WikiService
) : WikiRepository {

    override suspend fun getWikiInfo(artistName: String, albumName: String, mbId: String?) =
        wikiService.getWikiInfoAsync(artistName, albumName, mbId)
            ?.album
            ?.toDomainModel()

    override suspend fun searchWiki(phrase: String) =
        wikiService.searchWikiAsync(phrase)
            .results
            .albumMatches
            .album
            .map { it.toDomainModel() }
}