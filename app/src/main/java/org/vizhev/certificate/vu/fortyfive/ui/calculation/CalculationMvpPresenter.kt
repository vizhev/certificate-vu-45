package org.vizhev.certificate.vu.fortyfive.ui.calculation

import org.vizhev.certificate.vu.fortyfive.ui.base.MvpPresenter

interface CalculationMvpPresenter<V : CalculationMvpView> : MvpPresenter<V> {

    fun onCalculateResult()
}