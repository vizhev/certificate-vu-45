package org.vizhev.certificate.vu.fortyfive.data

import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent

interface Repository {

    fun setSavedCertificate(certificate: CertificateContent)

    fun getSavedCertificates(): List<CertificateContent>

    fun deleteCertificates(deletedCertificates: Set<Long>)
}