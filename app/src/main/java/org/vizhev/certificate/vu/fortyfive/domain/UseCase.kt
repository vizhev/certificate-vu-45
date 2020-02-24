package org.vizhev.certificate.vu.fortyfive.domain

import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent

interface UseCase {

    fun calculateCertificateResult(certificateContent: CertificateContent)

    fun saveCertificates(certificateContent: CertificateContent)

    fun loadSavedCertificates(): List<CertificateContent>

    fun deleteCertificate(deletedCertificates: Set<Long>)
}