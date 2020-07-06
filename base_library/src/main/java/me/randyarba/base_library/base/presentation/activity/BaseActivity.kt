package me.randyarba.base_library.base.presentation.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import timber.log.Timber

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : InjectionActivity(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}