package org.vizhev.certificate.vu.fortyfive.data.api

import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData

//TODO implement calculation
class AppCalculator : Calculator {

    private var weight: Double = 0.0
    private var coefficient: Double = 0.0
    private var ts: Double = 0.0
    private var scotches: Double = 0.0

    override fun calculateResult(certificateData: CertificateData): CertificateData {
        initValues(certificateData)
        certificateData.padsRequired = getPadsRequired()
        certificateData.handBrakesRequired = getHandBrakesRequired()
        return certificateData
    }

    private fun getPadsRequired(): String {
        val padsRequired = weight * coefficient
        if (padsRequired - padsRequired.toInt() > 0) {
            padsRequired + 1
        }
        return "$padsRequired (${coefficient * 100})"
    }

    private fun getHandBrakesRequired(): String {
        var handbrakes: Double = ((weight * ts / 100) - scotches) / 4
        if (handbrakes - handbrakes.toInt() > 0.0) {
            handbrakes++
        }
        return "BS - ${handbrakes.toInt()}HB"
    }

    private fun initValues(certificateData: CertificateData) {
        val brakePressureTwoAndHalf =       getValidInt(certificateData.pressingPadsTwoAndHalf)
        val brakePressureThreeAndHalf =     getValidInt(certificateData.pressingPadsThreeAndHalf)
        val brakePressureFive =             getValidInt(certificateData.pressingPadsFive)
        val brakePressureSix =              getValidInt(certificateData.pressingPadsSix)
        val brakePressureSixAndHalf =       getValidInt(certificateData.pressingPadsSixAndHalf)
        val brakePressureSeven =            getValidInt(certificateData.pressingPadsSeven)
        val brakePressureSevenAndHalf =     getValidInt(certificateData.pressingPadsSevenAndHalf)
        val brakePressureEight =            getValidInt(certificateData.pressingPadsEight)
        val brakePressureEightAndHalf =     getValidInt(certificateData.pressingPadsEightAndHalf)
        val brakePressureNine =             getValidInt(certificateData.pressingPadsNine)
        val brakePressureTen =              getValidInt(certificateData.pressingPadsTen)
        val brakePressureTwelve =           getValidInt(certificateData.pressingPadsTwelve)
        val brakePressureFifteen =          getValidInt(certificateData.pressingPadsFifteen)
        val totalAxes = brakePressureTwoAndHalf +
                brakePressureThreeAndHalf +
                brakePressureFive +
                brakePressureSix +
                brakePressureSixAndHalf +
                brakePressureSeven +
                brakePressureSevenAndHalf +
                brakePressureEight +
                brakePressureEightAndHalf +
                brakePressureNine +
                brakePressureTen +
                brakePressureTwelve +
                brakePressureFifteen
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