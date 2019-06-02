package org.vizhev.certificate.vu.fortyfive.data

import org.vizhev.certificate.vu.fortyfive.data.prefs.PreferencesHelper
import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent

class GeneralRepository(private val mPreferencesHelper: PreferencesHelper)
    : Repository {

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