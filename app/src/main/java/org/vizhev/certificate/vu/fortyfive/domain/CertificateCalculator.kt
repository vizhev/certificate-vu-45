package org.vizhev.certificate.vu.fortyfive.domain

import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent

interface CertificateCalculator {

    fun calculateResult(certificateContent: CertificateContent)
}