package org.vizhev.certificate.vu.fortyfive.domain

import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent

interface Repository {

    fun getCalculationResult(certificateContent: CertificateContent)

    fun setSavedCertificate(certificate: CertificateContent)

    fun getSavedCertificates(): List<CertificateContent>

    fun deleteCertificates(deletedCertificates: Set<Long>)
}