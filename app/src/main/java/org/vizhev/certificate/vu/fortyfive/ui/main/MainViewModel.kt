package org.vizhev.certificate.vu.fortyfive.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData

class MainViewModel : ViewModel() {

    private val liveData = MutableLiveData<CertificateData>()

    fun setCertificateDate(certificateData: CertificateData) {
        liveData.value = certificateData
    }

    fun getCertificateData() = liveData
}