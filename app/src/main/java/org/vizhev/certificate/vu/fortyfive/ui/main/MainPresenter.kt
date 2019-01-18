package org.vizhev.certificate.vu.fortyfive.ui.main

import org.vizhev.certificate.vu.fortyfive.ui.base.BasePresenter

class MainPresenter<V : MainMvpView> : BasePresenter<V>(), MainMvpPresenter<V> {

    private val adapter: MainAdapter = MainAdapter()

    override fun onCreateAdapter(): MainAdapter {
        return adapter
    }

    override fun onSetContent() {

    }
}