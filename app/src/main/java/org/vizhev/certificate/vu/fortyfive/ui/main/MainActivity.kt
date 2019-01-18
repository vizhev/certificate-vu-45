package org.vizhev.certificate.vu.fortyfive.ui.main

import android.os.Bundle
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.base.BaseActivity

class MainActivity : BaseActivity(), MainMvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}