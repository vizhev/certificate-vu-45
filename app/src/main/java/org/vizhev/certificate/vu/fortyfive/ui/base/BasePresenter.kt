package org.vizhev.certificate.vu.fortyfive.ui.base

open class BasePresenter<V : MvpView> : MvpPresenter<V> {

    private var mvpView : V? = null

    override fun onAttach(view: V?) {
        this.mvpView = view
    }

    override fun onDetach() {
        mvpView = null
    }

    override fun getView(): V? = mvpView
}