package org.vizhev.certificate.vu.fortyfive.ui.main

import org.vizhev.certificate.vu.fortyfive.ui.base.MvpView

interface MainMvpView : MvpView {

    fun startFragmentTransaction(fragmentTag: String, action: String)

}