package org.vizhev.certificate.vu.fortyfive.data.prefs

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Test
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent

class TestPreferencesHelper {

    @Test
    fun testSaveAndLoadHistory_wasEqualsSaveAndLoadList() {
        /*val saveIdList = mutableListOf<String>()
        val preferencesHelper: PreferencesHelper = AppPreferencesHelper(ApplicationProvider.getApplicationContext<Context>())
        for (i in 0..4) {
            val parameters = CertificateContent()
            saveIdList.add(i.toString())
            parameters.id = i.toString()
            parameters.totalAxes = i.toString()
            preferencesHelper.saveCertificate(parameters)
        }
        val loadIdList = mutableListOf<String>()
        val historyList = preferencesHelper.loadCertificates()
        for (parameters in historyList) {
            loadIdList.add(parameters.id)
        }
        assertEquals(saveIdList.size, loadIdList.size)
        assertEquals(saveIdList, loadIdList)
        for (i in 0..4) {
            assertEquals(saveIdList[i], loadIdList[i])
        }*/
    }
}