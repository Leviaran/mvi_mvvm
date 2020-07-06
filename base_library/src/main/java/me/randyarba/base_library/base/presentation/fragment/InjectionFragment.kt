package me.randyarba.base_library.base.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import me.randyarba.base_library.BuildConfig
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.kcontext

abstract class InjectionFragment (@LayoutRes layoutId: Int): Fragment(layoutId), KodeinAware {

    @SuppressWarnings("LeakingThisInConstructor")
    final override val kodeinContext = kcontext<Fragment>(this)

    final override val kodein by kodein()

    final override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kodeinTrigger?.trigger()
    }
}