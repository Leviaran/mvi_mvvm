package me.randyarba.feature_wiki.presentation

import androidx.fragment.app.Fragment
import coil.ImageLoader
import me.randyarba.base_library.base.di.KotlinViewModelProvider
import me.randyarba.feature_wiki.MODULE_NAME
import me.randyarba.feature_wiki.presentation.wikidetail.WikiDetailViewModel
import me.randyarba.feature_wiki.presentation.wikilist.WikiListViewModel
import me.randyarba.feature_wiki.presentation.wikilist.recyclerview.WikiAdapter
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    bind<WikiListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { WikiListViewModel(instance(), instance()) }
    }

    bind() from singleton { WikiAdapter() }

    bind() from singleton { ImageLoader(instance()) }


    bind<WikiDetailViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { WikiDetailViewModel(instance(), instance()) }
    }
}