package org.vizhev.certificate.vu.fortyfive.data.api

import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData

class AppCalculator : Calculator {

    private var weight: Double = 0.0
    private var coefficient: Double = 0.0
    private var ts: Double = 0.0
    private var scotches: Double = 0.0

    override fun calculateResult(certificateData: CertificateData): CertificateData {
        initValues(certificateData)
        certificateData.pressingPadsRequired = getPadsRequired()
        certificateData.handBrakesRequired = getHandBrakesRequired()
        return certificateData
    }

    private fun getPadsRequired(): String {
        val padsRequired = weight * coefficient
        if (padsRequired - padsRequired.toInt() > 0) {
            padsRequired + 1
        }
        return "$padsRequired (${(coefficient * 100).toInt()})"
    }

    private fun getHandBrakesRequired(): String {
        var handbrakes: Double = ((weight * ts / 100) - scotches) / 4
        if (handbrakes - handbrakes.toInt() > 0.0) {
            handbrakes++
        }
        return "BS - ${handbrakes.toInt()}HB"
    }

    private fun initValues(certificateData: CertificateData) {
        val axesTwoAndHalf =       getValidInt(certificateData.axesTwoAndHalf)
        val axesThreeAndHalf =     getValidInt(certificateData.axesThreeAndHalf)
        val axesFive =             getValidInt(certificateData.axesFive)
        val axesSix =              getValidInt(certificateData.axesSix)
        val axesSixAndHalf =       getValidInt(certificateData.axesSixAndHalf)
        val axesSeven =            getValidInt(certificateData.axesSeven)
        val axesSevenAndHalf =     getValidInt(certificateData.axesSevenAndHalf)
        val axesEight =            getValidInt(certificateData.axesEight)
        val axesEightAndHalf =     getValidInt(certificateData.axesEightAndHalf)
        val axesNine =             getValidInt(certificateData.axesNine)
        val axesTen =              getValidInt(certificateData.axesTen)
        val axesTwelve =           getValidInt(certificateData.axesTwelve)
        val axesFifteen =          getValidInt(certificateData.axesFifteen)
        val totalAxes = axesTwoAndHalf +
                axesThreeAndHalf +
                axesFive +
                axesSix +
                axesSixAndHalf +
                axesSeven +
                axesSevenAndHalf +
                axesEight +
                axesEightAndHalf +
                axesNine +
                axesTen +
                axesTwelve +
                axesFifteen
        certificateData.totalAxes = totalAxes.toString()
        weight = getValidDouble(certificateData.weight)
        coefficient = when (certificateData.isLoaded) {
            true -> if (totalAxes <= 350) 0.55 else 0.44
            else -> 0.33
        }
    }

    private fun getValidDouble(stringNumber: String): Double {
        var validDouble = 0.0
        try {
            var number = stringNumber
            if (number.contains(',')) {
                val validCharArray = CharArray(number.length)
                for (i in number.indices) {
                    val c = number[i]
                    if (c == ',') {
                        validCharArray[i] = '.'
                    } else {
                        validCharArray[i] = c
                    }
                }
                number = validCharArray.toString()
            }
            validDouble = number.toDouble()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        return validDouble
    }

    private fun getValidInt(stringNumber: String): Int {
        var validInt = 0
        try {
            validInt = stringNumber.toInt()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        return validInt
    }
}