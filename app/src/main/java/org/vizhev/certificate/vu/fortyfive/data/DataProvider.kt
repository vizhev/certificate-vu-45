package org.vizhev.certificate.vu.fortyfive.data

import org.vizhev.certificate.vu.fortyfive.data.api.Calculator

interface DataProvider {

    fun getCalculator(): Calculator
}