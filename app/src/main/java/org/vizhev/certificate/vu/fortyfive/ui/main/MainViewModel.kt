package org.vizhev.certificate.vu.fortyfive.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.vizhev.certificate.vu.fortyfive.data.DataProvider
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent
import org.vizhev.certificate.vu.fortyfive.ui.savedcertificates.SavedCertificatesAdapter
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(private val mDataProvider: DataProvider) : ViewModel() {

    private val mLiveData = MutableLiveData<CertificateContent>()
    private val mSavedCertificatesAdapter = SavedCertificatesAdapter()

    fun calculateResult(certificate: CertificateContent) {
        val certificateResult = mDataProvider.getCalculator().calculateResult(certificate)
        certificateResult.issueTime = getTime()
        certificateResult.date = getDate()
        mLiveData.value = certificateResult
    }

    fun getCertificateData() = mLiveData

    fun saveCertificate() {
        val certificate = mLiveData.value
        if (certificate != null) {
            certificate.id = System.currentTimeMillis()
            mDataProvider.setSavedCertificate(certificate)
        }
    }

    fun getSavedCertificatesAdapter(): SavedCertificatesAdapter {
        return mSavedCertificatesAdapter
    }

    fun loadSavedCertificates() {
        val savedCertificates = mDataProvider.getSavedCertificates()
        mSavedCertificatesAdapter.setContent(savedCertificates)
        mSavedCertificatesAdapter.notifyDataSetChanged()
    }

    private fun getTime(): String = SimpleDateFormat("HH : mm", Locale.getDefault())
            .format(Date())

    private fun getDate(): String = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            .format(Date())
}