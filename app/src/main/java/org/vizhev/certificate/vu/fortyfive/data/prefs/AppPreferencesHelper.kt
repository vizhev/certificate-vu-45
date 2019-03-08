package org.vizhev.certificate.vu.fortyfive.data.prefs

import android.content.Context
import com.ironz.binaryprefs.BinaryPreferencesBuilder
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent

class AppPreferencesHelper(private val mContext: Context) : PreferencesHelper {

    companion object {
        private const val PREFS_SAVED_CERTIFICATES_KEYS: String = "saved_certificates_keys"
        private const val PREFS_SAVED_CERTIFICATES: String = "saved_certificates"
    }

    override fun saveCertificate(certificateContent: CertificateContent) {
        BinaryPreferencesBuilder(mContext)
            .name(PREFS_SAVED_CERTIFICATES_KEYS)
            .build()
            .edit()
            .putLong(certificateContent.id.toString(), certificateContent.id)
            .apply()
        BinaryPreferencesBuilder(mContext)
            .name(PREFS_SAVED_CERTIFICATES)
            .registerPersistable(certificateContent.id.toString(), CertificateContent::class.java)
            .build()
            .edit()
            .putPersistable(certificateContent.id.toString(), certificateContent)
            .apply()
    }

    override fun loadCertificates(): List<CertificateContent> {
        val certificatesContentList: MutableList<CertificateContent> = mutableListOf()
        val prefHistoryKeys = BinaryPreferencesBuilder(mContext)
            .name(PREFS_SAVED_CERTIFICATES_KEYS)
            .build()
        val historyKeys = prefHistoryKeys.all.keys.sorted()
        historyKeys.forEach {
            val certificateContent = BinaryPreferencesBuilder(mContext)
                .name(PREFS_SAVED_CERTIFICATES)
                .registerPersistable(it, CertificateContent::class.java)
                .build()
                .getPersistable(it, CertificateContent())
            certificatesContentList.add(certificateContent)
        }
        return certificatesContentList
    }

    override fun deleteCertificates(idList: List<Long>) {
        idList.forEach {
            BinaryPreferencesBuilder(mContext)
                .name(PREFS_SAVED_CERTIFICATES_KEYS)
                .build()
                .edit()
                .remove(it.toString())
                .apply()
            BinaryPreferencesBuilder(mContext)
                .name(PREFS_SAVED_CERTIFICATES)
                .registerPersistable(it.toString(), CertificateContent::class.java)
                .build()
                .edit()
                .clear()
                .apply()
        }
    }
}