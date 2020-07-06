package com.igorwojda.showcase.feature.album.data.model

import com.squareup.moshi.Json
import me.randyarba.feature_wiki.data.enum.WikiImageSize
import me.randyarba.feature_wiki.domain.model.WikiDomainModel

internal data class WikiDataModel(
    @field:Json(name = "mbid") val mbId: String?,
    val name: String,
    val artist: String,
    val wiki: AlbumWikiDataModel?,
    @field:Json(name = "image") val images: List<WikiImageDataModel>?
)

internal fun WikiDataModel.toDomainModel(): WikiDomainModel {
    val images = this.images
        ?.filterNot { it.size == WikiImageSize.UNKNOWN || it.url.isBlank() }
        ?.map { it.toDomainModel() }

    return WikiDomainModel(
        mbId = this.mbId,
        name = this.name,
        artist = this.artist,
        images = images ?: listOf(),
        wiki = this.wiki?.toDomainModel()
    )
}
