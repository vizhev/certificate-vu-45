package org.vizhev.certificate.vu.fortyfive.data.prefs

import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent

interface PreferencesHelper {

    fun saveCertificate(certificateContent: CertificateContent)

    fun loadCertificates(): List<CertificateContent>

    fun deleteCertificates(idList: List<Long>)
}