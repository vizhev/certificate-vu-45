package org.vizhev.certificate.vu.fortyfive.data

import org.vizhev.certificate.vu.fortyfive.data.api.Calculator
import org.vizhev.certificate.vu.fortyfive.data.prefs.PreferencesHelper

class AppDataProvider (
        private val calculator: Calculator,
        private val preferencesHelper: PreferencesHelper
) : DataProvider {

    override fun getCalculator(): Calculator {
        return calculator
    }
}