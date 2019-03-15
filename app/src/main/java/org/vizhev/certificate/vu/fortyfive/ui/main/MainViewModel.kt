package org.vizhev.certificate.vu.fortyfive.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.vizhev.certificate.vu.fortyfive.data.DataProvider
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent
import org.vizhev.certificate.vu.fortyfive.ui.main.fragments.savedcertificates.SavedCertificatesAdapter
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

    fun loadSavedCertificates() {
        val savedCertificates = mDataProvider.getSavedCertificates()
        mSavedCertificatesAdapter.setContent(savedCertificates)
    }

    fun deleteCertificates() {
        val selectedItemsSet = mutableSetOf<Long>()
        selectedItemsSet.addAll(mSavedCertificatesAdapter.getSelectedItems())
        mDataProvider.deleteCertificates(selectedItemsSet)
        mSavedCertificatesAdapter.removeSelectedItems()
    }

    fun saveCertificate(): Boolean {
        val certificateContent = mLiveData.value
        if (certificateContent != null) {
            if (mSavedCertificatesAdapter.getContent().contains(certificateContent)) {
                return false
            }
            certificateContent.id = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date()).toLong()
            mDataProvider.setSavedCertificate(certificateContent)
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

    private fun getTime(): String {
        return SimpleDateFormat("HH : mm", Locale.getDefault()).format(Date())
    }

    private fun getDate(): String {
        return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
    }

    abstract class UiState {

        companion object {
            var isSaveActionVisible: Boolean = false
            var isDeleteActionVisible: Boolean = false
            var isSavedItemSelected: Boolean = false
            var isFabVisible: Boolean = false
            var isResultViewOpen: Boolean = false
        }
    }
}