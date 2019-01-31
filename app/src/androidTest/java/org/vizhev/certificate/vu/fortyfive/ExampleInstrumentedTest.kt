package org.vizhev.certificate.vu.fortyfive

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.ironz.binaryprefs.BinaryPreferencesBuilder

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.vizhev.certificate.vu.fortyfive.dataclasses.Parameters
import com.ironz.binaryprefs.Preferences

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("org.vizhev.certificate.vu.fortyfive", appContext.packageName)

        val parameters = Parameters()
        parameters.date = "12"
        parameters.tailWagonNumber = "133"

        val preference: Preferences = BinaryPreferencesBuilder(appContext)
                .registerPersistable(Parameters.KEY, Parameters::class.java)
                .allowBuildOnBackgroundThread()
                .build()

        preference.edit().putPersistable("new", parameters).apply()

        val parametersNew = preference.getPersistable("new", Parameters())

        assertEquals("12", parametersNew.date)
        assertEquals("133", parametersNew.tailWagonNumber)
    }
}
