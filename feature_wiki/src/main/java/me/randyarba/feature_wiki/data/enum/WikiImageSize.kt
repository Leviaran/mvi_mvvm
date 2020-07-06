package me.randyarba.feature_wiki.data.enum

import com.squareup.moshi.Json
import me.randyarba.feature_wiki.domain.enum.WikiDomainImageSize

internal enum class WikiImageSize {

    @field:Json(name = "medium")
    MEDIUM,
    @field:Json(name = "small")
    SMALL,
    @field:Json(name = "large")
    LARGE,
    @field:Json(name = "extralarge")
    EXTRA_LARGE,
    @field:Json(name = "mega")
    MEGA,
    @field:Json(name = "")
    UNKNOWN
}

internal fun WikiImageSize.toDomainEnum() = WikiDomainImageSize.valueOf(this.name)
