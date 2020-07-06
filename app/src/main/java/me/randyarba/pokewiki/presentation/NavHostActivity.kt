package me.randyarba.pokewiki.presentation

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import me.randyarba.base_library.base.presentation.activity.BaseActivity
import me.randyarba.base_library.base.presentation.navigation.NavigationManager
import me.randyarba.pokewiki.R
import org.kodein.di.generic.instance

class NavHostActivity : BaseActivity(R.layout.activity_main) {

    private val navController get() = navHostFragment.findNavController()

    private val navManager: NavigationManager by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
        initNavManager()
    }

    private fun initBottomNavigation() {
        bottomNav.setupWithNavController(navController)
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            navController.navigate(it)
        }
    }
}