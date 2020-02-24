package org.vizhev.certificate.vu.fortyfive.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent
import org.vizhev.certificate.vu.fortyfive.ui.Interactor
import org.vizhev.certificate.vu.fortyfive.ui.savedcertificates.SavedCertificatesAdapter
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(
    private val interactor: Interactor
) : ViewModel() {

    private val mLiveData = MutableLiveData<CertificateContent>()
    private val mSavedCertificatesAdapter = SavedCertificatesAdapter()

    fun calculateResult(certificateContent: CertificateContent) {
        interactor.calculateCertificateResult(certificateContent)
        certificateContent.issueTime = SimpleDateFormat("HH : mm", Locale.getDefault()).format(Date())
        certificateContent.date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        mLiveData.value = certificateContent
    }

    fun loadSavedCertificates() {
        val savedCertificates = interactor.loadSavedCertificates()
        mSavedCertificatesAdapter.setContent(savedCertificates)
    }

    fun deleteCertificates() {
        val selectedItemsSet = mutableSetOf<Long>()
        selectedItemsSet.addAll(mSavedCertificatesAdapter.getSelectedItems())
        interactor.deleteCertificate(selectedItemsSet)
        mSavedCertificatesAdapter.removeSelectedItems()
    }

    fun saveCertificate(): Boolean {
        val certificateContent = mLiveData.value
        if (certificateContent != null) {
            if (mSavedCertificatesAdapter.getContent().contains(certificateContent)) {
                return false
            }
            certificateContent.id = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date()).toLong()
            interactor.saveCertificates(certificateContent)
            mSavedCertificatesAdapter.setItem(certificateContent)
            return true
        }
        return false
    }

    fun getCertificateData(): LiveData<CertificateContent> {
        return mLiveData
    }

    fun getSavedCertificatesAdapter(): SavedCertificatesAdapter {
        return mSavedCertificatesAdapter
    }
}