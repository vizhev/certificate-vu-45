package org.vizhev.certificate.vu.fortyfive.ui.main

import org.vizhev.certificate.vu.fortyfive.ui.base.BasePresenter

class MainPresenter<V : MainMvpView> : BasePresenter<V>(), MainMvpPresenter<V> {

    private var isResultVisible: Boolean = false

    override fun isResultViewVisible(): Boolean = isResultVisible

    override fun onSetContent() {

    }

    override fun onCalculateResult() {
        isResultVisible = !isResultVisible
        getView()!!.setResult("")
    }
}