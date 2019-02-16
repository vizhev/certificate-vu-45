package org.vizhev.certificate.vu.fortyfive.data.prefs

import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData

interface PreferencesHelper {

    fun saveHistory(parameters: CertificateData)

    fun loadHistory(): List<CertificateData>
}