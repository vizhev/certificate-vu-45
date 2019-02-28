package org.vizhev.certificate.vu.fortyfive.data.api

import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData

interface Calculator {

    fun calculateResult(certificateData: CertificateData): CertificateData
}