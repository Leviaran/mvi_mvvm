package me.randyarba.base_library.base

import me.randyarba.base_library.base.presentation.navigation.NavigationManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

internal const val MODULE_NAME = "Base"

val baseModule = Kodein.Module("${MODULE_NAME}Module") {
    bind() from singleton { NavigationManager() }
}