package org.vizhev.certificate.vu.fortyfive.data.api

import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent

interface Calculator {

    fun calculateResult(certificateContent: CertificateContent): CertificateContent
}