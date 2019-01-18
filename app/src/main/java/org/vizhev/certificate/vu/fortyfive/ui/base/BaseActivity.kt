package org.vizhev.certificate.vu.fortyfive.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.vizhev.certificate.vu.fortyfive.App
import org.vizhev.certificate.vu.fortyfive.di.components.ActivityComponent
import org.vizhev.certificate.vu.fortyfive.di.components.DaggerActivityComponent
import org.vizhev.certificate.vu.fortyfive.di.modules.ActivityModule

abstract class BaseActivity : AppCompatActivity(), MvpView {

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = if (savedInstanceState == null) DaggerActivityComponent
            .builder()
            .applicationComponent((application as App).getApplicationComponent())
            .activityModule(ActivityModule())
            .build()
        else lastCustomNonConfigurationInstance as ActivityComponent
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return activityComponent
    }

    fun getActivityComponent() : ActivityComponent = activityComponent
}