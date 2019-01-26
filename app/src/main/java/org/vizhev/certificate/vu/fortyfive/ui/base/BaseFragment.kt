package org.vizhev.certificate.vu.fortyfive.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import org.vizhev.certificate.vu.fortyfive.di.components.ActivityComponent

abstract class BaseFragment : Fragment() {

    private lateinit var mActivityComponent: ActivityComponent

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivityComponent = (activity as BaseActivity).getActivityComponent()
        retainInstance = true
    }

    protected fun getActivityComponent(): ActivityComponent = mActivityComponent

}