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

    fun calculateResult(certificateContent: CertificateContent) {
        val certificateResult = mDataProvider.getCalculator().calculateResult(certificateContent)
        certificateResult.issueTime = getTime()
        certificateResult.date = getDate()
        mLiveData.value = certificateResult
    }

    fun saveCertificate() {
        val certificateContent = mLiveData.value
        if (certificateContent != null) {
            certificateContent.id = System.currentTimeMillis()
            mDataProvider.setSavedCertificate(certificateContent)
        }
    }

    fun loadSavedCertificates() {
        val savedCertificates = mDataProvider.getSavedCertificates()
        mSavedCertificatesAdapter.setContent(savedCertificates)
        mSavedCertificatesAdapter.notifyDataSetChanged()
    }

    fun getCertificateData() = mLiveData

    fun getSavedCertificatesAdapter() = mSavedCertificatesAdapter

    private fun getTime() = SimpleDateFormat("HH : mm", Locale.getDefault()).format(Date())

    private fun getDate() = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
}