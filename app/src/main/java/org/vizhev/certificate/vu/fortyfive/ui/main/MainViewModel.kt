package org.vizhev.certificate.vu.fortyfive.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.vizhev.certificate.vu.fortyfive.data.DataProvider
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData
import org.vizhev.certificate.vu.fortyfive.ui.savedcertificates.SavedCertificatesAdapter
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(private val dataProvider: DataProvider): ViewModel() {

    private val mLiveData = MutableLiveData<CertificateData>()
    private val mSavedCertificatesAdapter = SavedCertificatesAdapter()

    fun calculateResult(certificateData: CertificateData) {
        val certificateResult = dataProvider.getCalculator().calculateResult(certificateData)
        certificateResult.time = getTime()
        certificateResult.date = getDate()
        mLiveData.value = certificateResult
    }

    fun getCertificateData() = mLiveData

    fun getSavedCertificatesAdapter(): SavedCertificatesAdapter {

        return mSavedCertificatesAdapter
    }

    private fun getTime(): String {
        return  SimpleDateFormat(
                "HH : mm",
                Locale.getDefault()
        ).format(Date())
    }

    private fun getDate(): String {
        return SimpleDateFormat(
                "dd.MM.yyyy",
                Locale.getDefault()
        ).format(Date())
    }
}