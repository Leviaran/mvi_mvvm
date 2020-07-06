package me.randyarba.feature_wiki.data

import me.randyarba.feature_wiki.MODULE_NAME
import me.randyarba.feature_wiki.data.repository.WikiRepositoryImplement
import me.randyarba.feature_wiki.data.retrofit.service.WikiService
import me.randyarba.feature_wiki.domain.repository.WikiRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${MODULE_NAME}DataModule") {

    bind<WikiRepository>() with singleton { WikiRepositoryImplement(instance()) }

    bind() from singleton { instance<Retrofit>().create(WikiService::class.java) }
}