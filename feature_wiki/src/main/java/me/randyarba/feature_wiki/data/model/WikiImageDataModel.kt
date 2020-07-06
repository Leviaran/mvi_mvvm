package com.igorwojda.showcase.feature.album.data.model

import com.squareup.moshi.Json
import me.randyarba.feature_wiki.data.enum.WikiImageSize
import me.randyarba.feature_wiki.data.enum.toDomainEnum
import me.randyarba.feature_wiki.domain.model.WikiImageDomainModel

internal data class WikiImageDataModel(
    @field:Json(name = "#text") val url: String,
    val size: WikiImageSize
)

internal fun WikiImageDataModel.toDomainModel() = WikiImageDomainModel(
    url = this.url,
    size = this.size.toDomainEnum()
)
