package org.vizhev.certificate.vu.fortyfive.ui.calculation

import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData
import org.vizhev.certificate.vu.fortyfive.ui.base.MvpView

interface CalculationMvpView : MvpView {

    fun getInputData(): CertificateData

    fun setResult(certificateData: CertificateData)
}