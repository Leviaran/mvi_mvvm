package me.randyarba.feature_wiki.domain.model

import me.randyarba.feature_wiki.domain.enum.WikiDomainImageSize

internal data class WikiImageDomainModel(
    val url: String,
    val size: WikiDomainImageSize
)