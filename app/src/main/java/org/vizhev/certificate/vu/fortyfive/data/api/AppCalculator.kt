package org.vizhev.certificate.vu.fortyfive.data.api

import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData

class AppCalculator : Calculator {

    private var mWeight: Double = 0.0
    private var mCoefficient: Double = 0.0
    private var mSlopeFactor: Double = 0.0
    private var mAxesTwoAndHalf: Int =   0
    private var mAxesThreeAndHalf: Int = 0
    private var mAxesFive: Int = 0
    private var mAxesSix: Int = 0
    private var mAxesSixAndHalf: Int = 0
    private var mAxesSeven: Int = 0
    private var mAxesSevenAndHalf = 0
    private var mAxesEight: Int =0
    private var mAxesEightAndHalf:Int = 0
    private var mAxesNine: Int = 0
    private var mAxesTen: Int = 0
    private var mAxesTwelve: Int = 0
    private var mAxesFifteen: Int = 0
    private var mTotalAxes: Int = 0

    override fun calculateResult(certificateData: CertificateData): CertificateData {
        initValues(certificateData)
        certificateData.pressingPadsTwoAndHalf = getPressingPads(mAxesTwoAndHalf, 2.5)
        certificateData.pressingPadsThreeAndHalf = getPressingPads(mAxesThreeAndHalf, 3.5)
        certificateData.pressingPadsFive = getPressingPads(mAxesFive, 5.0)
        certificateData.pressingPadsSix = getPressingPads(mAxesSix, 6.0)
        certificateData.pressingPadsSixAndHalf = getPressingPads(mAxesSixAndHalf, 6.5)
        certificateData.pressingPadsSeven = getPressingPads(mAxesSeven, 7.0)
        certificateData.pressingPadsSevenAndHalf = getPressingPads(mAxesSevenAndHalf, 7.5)
        certificateData.pressingPadsEight = getPressingPads(mAxesEight, 8.0)
        certificateData.pressingPadsEightAndHalf = getPressingPads(mAxesEightAndHalf, 8.5)
        certificateData.pressingPadsNine = getPressingPads(mAxesNine, 9.0)
        certificateData.pressingPadsTen = getPressingPads(mAxesTen, 10.0)
        certificateData.pressingPadsTwelve = getPressingPads(mAxesTwelve, 12.0)
        certificateData.pressingPadsFifteen = getPressingPads(mAxesFifteen, 15.0)
        certificateData.totalAxes = mTotalAxes.toString()
        certificateData.pressingPadsRequired = getPressingPadsRequired()
        certificateData.handBrakesRequired = getHandBrakesRequired()
        return certificateData
    }

    private fun getPressingPads(axesCount: Int, coefficient: Double): String {
        val pressingPads = axesCount * coefficient
        if (pressingPads - pressingPads.toInt() > 0) {
            pressingPads + 1
        }
        return "${pressingPads.toInt()}"
    }

    private fun getPressingPadsRequired(): String {
        var pressingPadsRequired = mWeight * mCoefficient
        if (pressingPadsRequired - pressingPadsRequired.toInt() > 0) {
            pressingPadsRequired++
        }
        return "${pressingPadsRequired.toInt()} (${(mCoefficient * 100).toInt()})"
    }

    private fun getHandBrakesRequired(): String {
        var handbrakes = mWeight * mSlopeFactor / 100
        if (handbrakes - handbrakes.toInt() > 0.0) {
            handbrakes++
        }
        return "${handbrakes.toInt()}"
    }

    private fun initValues(certificateData: CertificateData) {
        mAxesTwoAndHalf =       getValidInt(certificateData.axesTwoAndHalf)
        mAxesThreeAndHalf =     getValidInt(certificateData.axesThreeAndHalf)
        mAxesFive =             getValidInt(certificateData.axesFive)
        mAxesSix =              getValidInt(certificateData.axesSix)
        mAxesSixAndHalf =       getValidInt(certificateData.axesSixAndHalf)
        mAxesSeven =            getValidInt(certificateData.axesSeven)
        mAxesSevenAndHalf =     getValidInt(certificateData.axesSevenAndHalf)
        mAxesEight =            getValidInt(certificateData.axesEight)
        mAxesEightAndHalf =     getValidInt(certificateData.axesEightAndHalf)
        mAxesNine =             getValidInt(certificateData.axesNine)
        mAxesTen =              getValidInt(certificateData.axesTen)
        mAxesTwelve =           getValidInt(certificateData.axesTwelve)
        mAxesFifteen =          getValidInt(certificateData.axesFifteen)
        mTotalAxes =
                mAxesTwoAndHalf +
                mAxesThreeAndHalf +
                mAxesFive +
                mAxesSix +
                mAxesSixAndHalf +
                mAxesSeven +
                mAxesSevenAndHalf +
                mAxesEight +
                mAxesEightAndHalf +
                mAxesNine +
                mAxesTen +
                mAxesTwelve +
                mAxesFifteen
        mWeight = getValidDouble(certificateData.weight)
        mSlopeFactor = getValidDouble(certificateData.slopeFactor)
        mCoefficient = when (certificateData.isLoaded) {
            true -> if (mTotalAxes <= 350) 0.55 else 0.44
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