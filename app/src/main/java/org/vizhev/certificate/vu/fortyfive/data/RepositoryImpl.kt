package org.vizhev.certificate.vu.fortyfive.data

import org.vizhev.certificate.vu.fortyfive.domain.calculator.CertificateCalculator
import org.vizhev.certificate.vu.fortyfive.data.prefs.PreferencesHelper
import org.vizhev.certificate.vu.fortyfive.domain.Repository
import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent

class RepositoryImpl(
    private val mPreferencesHelper: PreferencesHelper,
    private val certificateCalculator: CertificateCalculator
) : Repository {

    override fun getCalculationResult(certificateContent: CertificateContent) {
        certificateCalculator.calculateResult(certificateContent)
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