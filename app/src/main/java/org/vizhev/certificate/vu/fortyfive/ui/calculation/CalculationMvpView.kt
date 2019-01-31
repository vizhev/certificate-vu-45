package org.vizhev.certificate.vu.fortyfive.ui.calculation

import org.vizhev.certificate.vu.fortyfive.dataclasses.Parameters
import org.vizhev.certificate.vu.fortyfive.ui.base.MvpView

interface CalculationMvpView : MvpView {

    fun getInputData(): Parameters

    fun setResult(parameters: Parameters)
}