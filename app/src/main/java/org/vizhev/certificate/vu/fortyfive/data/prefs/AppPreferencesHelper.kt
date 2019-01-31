package org.vizhev.certificate.vu.fortyfive.data.prefs

import android.content.Context
import com.ironz.binaryprefs.BinaryPreferencesBuilder
import com.ironz.binaryprefs.Preferences
import org.vizhev.certificate.vu.fortyfive.dataclasses.Parameters

class AppPreferencesHelper(context: Context) : PreferencesHelper {

    private val preferences: Preferences? = BinaryPreferencesBuilder(context)
            .registerPersistable(Parameters.KEY, Parameters::class.java)
            .build()

}