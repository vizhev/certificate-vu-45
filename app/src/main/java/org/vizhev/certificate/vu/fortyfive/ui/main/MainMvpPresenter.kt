package org.vizhev.certificate.vu.fortyfive.ui.main

import org.vizhev.certificate.vu.fortyfive.ui.base.MvpPresenter

interface MainMvpPresenter<V : MainMvpView> : MvpPresenter<V> {

    fun onStartFragmentTransaction(fragmentTag: String, action: String)

    fun getCurrentFragmentTag(): String

    fun getBackStackCount(): Int
}