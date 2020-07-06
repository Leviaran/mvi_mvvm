package me.randyarba.pokewiki.feature

import me.randyarba.pokewiki.BuildConfig

@Suppress("detekt.UnsafeCast")
object FeatureManager {

    private const val featurePackagePrefix = "me.randyarba"
    val kodeinModules = BuildConfig.FEATURE_MODULE_NAMES
        .map { "$featurePackagePrefix.feature_$it.FeatureKodeinModule" }
        .map {
            try {
                Class.forName(it).kotlin.objectInstance as KodeinModuleProvider
            } catch (e: ClassNotFoundException) {
                throw ClassNotFoundException("Kodein module class not found $it")
            }
        }
        .map { it.kodeinModule }
}
