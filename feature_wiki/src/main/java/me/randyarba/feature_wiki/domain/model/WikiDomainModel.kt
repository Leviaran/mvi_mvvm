package me.randyarba.feature_wiki.domain.model

import me.randyarba.feature_wiki.domain.enum.WikiDomainImageSize

internal data class WikiDomainModel(
    val name: String,
    val artist: String,
    val images: List<WikiImageDomainModel>,
    val wiki: WikiWikiDomainModel? = null,
    val mbId: String? = null
) {
    fun getDefaultImageUrl() = images.firstOrNull { it.size == WikiDomainImageSize.EXTRA_LARGE }?.url
}