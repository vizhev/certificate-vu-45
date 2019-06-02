package org.vizhev.certificate.vu.fortyfive.data.prefs

import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent

interface PreferencesHelper {

    fun saveCertificate(certificateContent: CertificateContent)

    fun loadCertificates(): List<CertificateContent>

    fun deleteCertificates(deletedCertificatesId: Set<Long>)
}