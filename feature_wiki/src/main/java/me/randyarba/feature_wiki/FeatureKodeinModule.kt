package me.randyarba.feature_wiki

import me.randyarba.feature_wiki.data.dataModule
import me.randyarba.feature_wiki.domain.domainModule
import me.randyarba.feature_wiki.presentation.presentationModule
import me.randyarba.pokewiki.feature.KodeinModuleProvider
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Wiki"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${MODULE_NAME}Module") {
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}