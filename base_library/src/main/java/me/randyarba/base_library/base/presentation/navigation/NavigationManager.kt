package me.randyarba.base_library.base.presentation.navigation

import androidx.navigation.NavDirections

class NavigationManager {
    private var navListener : ((navDirection: NavDirections) -> Unit)? = null

    fun navigateTo(navDirection: NavDirections) {
        navListener?.invoke(navDirection)
    }

    fun setOnNavEvent(navEventListener: (navDirections: NavDirections) -> Unit) {
        this.navListener = navEventListener
    }
}