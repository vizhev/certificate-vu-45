package org.vizhev.certificate.vu.fortyfive.data.prefs

import android.content.Context
import com.ironz.binaryprefs.BinaryPreferencesBuilder
import com.ironz.binaryprefs.Preferences
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData
import java.util.*

class AppPreferencesHelper(context: Context) : PreferencesHelper {

    private val preferences: Preferences? = BinaryPreferencesBuilder(context)
            .registerPersistable(CertificateData.KEY, CertificateData::class.java)
            .allowBuildOnBackgroundThread()
            .build()

    override fun saveHistory(parameters: CertificateData) {
        preferences!!
                .edit()
                .putPersistable(parameters.id, parameters)
                .apply()
    }

    override fun loadHistory(): List<CertificateData> {
        val historyList: MutableList<CertificateData> = mutableListOf()
        val historyKeys = preferences!!.keys().toTypedArray()
        Arrays.sort(historyKeys)
        historyKeys.forEach {
            val parameters = preferences.getPersistable(it, CertificateData())
            historyList.add(parameters)
        }
        return historyList
    }
}