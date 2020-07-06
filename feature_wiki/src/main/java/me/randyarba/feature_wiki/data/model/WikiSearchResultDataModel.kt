package com.igorwojda.showcase.feature.album.data.model

import com.squareup.moshi.Json

internal data class WikiSearchResultDataModel(
    @field:Json(name = "albummatches") val albumMatches: WikiListDataModel
)
