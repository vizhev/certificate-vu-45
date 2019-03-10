package org.vizhev.certificate.vu.fortyfive.data

import org.vizhev.certificate.vu.fortyfive.data.api.Calculator
import org.vizhev.certificate.vu.fortyfive.data.prefs.PreferencesHelper
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent

class AppDataProvider(private val mCalculator: Calculator, private val mPreferencesHelper: PreferencesHelper)
    : DataProvider {

    override fun getCalculator(): Calculator {
        return mCalculator
    }

    override fun setSavedCertificate(certificate: CertificateContent) {
        mPreferencesHelper.saveCertificate(certificate)
    }

    override fun getSavedCertificates(): List<CertificateContent> {
        return mPreferencesHelper.loadCertificates()
    }

    override fun deleteCertificates(deletedCertificates: Set<Long>) {
        mPreferencesHelper.deleteCertificates(deletedCertificates)
    }
}