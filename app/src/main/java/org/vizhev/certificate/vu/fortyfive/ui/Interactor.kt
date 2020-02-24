package org.vizhev.certificate.vu.fortyfive.ui

import org.vizhev.certificate.vu.fortyfive.domain.Repository
import org.vizhev.certificate.vu.fortyfive.domain.UseCase
import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent

class Interactor(private val repository: Repository) : UseCase {

    override fun calculateCertificateResult(certificateContent: CertificateContent) {
        repository.getCalculationResult(certificateContent)
    }

    override fun saveCertificates(certificateContent: CertificateContent) {
        repository.setSavedCertificate(certificateContent)
    }

    override fun loadSavedCertificates(): List<CertificateContent> {
        return repository.getSavedCertificates()
    }

    override fun deleteCertificate(deletedCertificates: Set<Long>) {
        repository.deleteCertificates(deletedCertificates)
    }
}