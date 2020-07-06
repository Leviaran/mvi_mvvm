package me.randyarba.feature_wiki.domain

import me.randyarba.feature_wiki.MODULE_NAME
import me.randyarba.feature_wiki.domain.usecase.WikiListUseCase
import me.randyarba.feature_wiki.domain.usecase.WikiUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind() from singleton { WikiListUseCase(instance()) }

    bind() from singleton { WikiUseCase(instance()) }
}