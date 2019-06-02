package org.vizhev.certificate.vu.fortyfive.data.prefs

import android.content.Context
import android.os.Environment
import com.ironz.binaryprefs.BinaryPreferencesBuilder
import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent
import java.io.File
import java.io.IOException

class AppPreferencesHelper(private val mContext: Context) : PreferencesHelper {

    companion object {
        private const val PREFS_SAVED_CERTIFICATES_KEYS = "saved_certificates_keys"
    }

    override fun saveCertificate(certificateContent: CertificateContent) {
        BinaryPreferencesBuilder(mContext)
                .name(PREFS_SAVED_CERTIFICATES_KEYS)
                .build()
                .edit()
                .putLong(certificateContent.id.toString(), certificateContent.id)
                .apply()
        BinaryPreferencesBuilder(mContext)
                .name(certificateContent.id.toString())
                .registerPersistable(certificateContent.id.toString(), CertificateContent::class.java)
                .build()
                .edit()
                .putPersistable(certificateContent.id.toString(), certificateContent)
                .apply()
    }

    override fun loadCertificates(): List<CertificateContent> {
        val certificatesContentList: MutableList<CertificateContent> = mutableListOf()
        val prefCertificatesKeys = BinaryPreferencesBuilder(mContext)
                .name(PREFS_SAVED_CERTIFICATES_KEYS)
                .allowBuildOnBackgroundThread()
                .build()
        val certificatesKeys = prefCertificatesKeys.all.keys.sorted().reversed()
        certificatesKeys.forEach {
            val certificateContent = BinaryPreferencesBuilder(mContext)
                    .name(it.toString())
                    .registerPersistable(it, CertificateContent::class.java)
                    .build()
                    .getPersistable(it, CertificateContent())
            certificatesContentList.add(certificateContent)
        }
        return certificatesContentList
    }

    override fun deleteCertificates(deletedCertificatesId: Set<Long>) {
        val prefCertificatesKeys = BinaryPreferencesBuilder(mContext)
                .name(PREFS_SAVED_CERTIFICATES_KEYS)
                .build()
        deletedCertificatesId.forEach {
            prefCertificatesKeys.edit()
                    .remove(it.toString())
                    .apply()
            val prefsPath = "${Environment.getDataDirectory().path}/data/${mContext.packageName}/files/preferences/$it"
            val prefFile = File(prefsPath)
            try {
                prefFile.deleteRecursively()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}