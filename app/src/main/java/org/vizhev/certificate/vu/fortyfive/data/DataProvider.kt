package org.vizhev.certificate.vu.fortyfive.data

import org.vizhev.certificate.vu.fortyfive.data.api.Calculator
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent

interface DataProvider {

    fun getCalculator(): Calculator

    fun setSavedCertificate(certificate: CertificateContent)

    fun getSavedCertificates(): List<CertificateContent>

    fun deleteCertificates(deletedCertificates: Set<Long>)
}