package com.igorwojda.showcase.feature.album.data.model

import me.randyarba.feature_wiki.domain.model.WikiWikiDomainModel

internal data class AlbumWikiDataModel(
    val published: String,
    val summary: String
)

internal fun AlbumWikiDataModel.toDomainModel() = WikiWikiDomainModel(
    published = this.published,
    summary = this.summary
)
