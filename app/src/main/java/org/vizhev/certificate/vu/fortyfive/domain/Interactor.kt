package org.vizhev.certificate.vu.fortyfive.domain

import org.vizhev.certificate.vu.fortyfive.data.Repository
import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent

class Interactor(val repository: Repository) {

    private val certificateCalculator: CertificateCalculator = AppCertificateCalculator()

    fun calculateCertificateResult(certificateContent: CertificateContent) {
        certificateCalculator.calculateResult(certificateContent)
    }

    fun saveCertificates(certificateContent: CertificateContent) {
        repository.setSavedCertificate(certificateContent)
    }

    fun loadSavedCertificates(): List<CertificateContent> {
        return repository.getSavedCertificates()
    }

    fun deleteCertificate(deletedCertificates: Set<Long>) {
        repository.deleteCertificates(deletedCertificates)
    }
}