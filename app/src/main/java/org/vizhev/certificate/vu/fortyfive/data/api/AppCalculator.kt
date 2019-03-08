package org.vizhev.certificate.vu.fortyfive.data.api

import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent

class AppCalculator : Calculator {

    private var mWeight: Double = 0.0
    private var mCoefficient: Double = 0.0
    private var mSlopeFactor: Double = 0.0
    private var mAxesTwoAndHalf: Int = 0
    private var mAxesThreeAndHalf: Int = 0
    private var mAxesFive: Int = 0
    private var mAxesSix: Int = 0
    private var mAxesSixAndHalf: Int = 0
    private var mAxesSeven: Int = 0
    private var mAxesSevenAndHalf = 0
    private var mAxesEight: Int = 0
    private var mAxesEightAndHalf: Int = 0
    private var mAxesNine: Int = 0
    private var mAxesTen: Int = 0
    private var mAxesTwelve: Int = 0
    private var mAxesFifteen: Int = 0
    private var mTotalAxes: Int = 0

    override fun calculateResult(certificateContent: CertificateContent): CertificateContent {
        initValues(certificateContent)
        certificateContent.pressingPadsTwoAndHalf = getPressingPads(mAxesTwoAndHalf, 2.5)
        certificateContent.pressingPadsThreeAndHalf = getPressingPads(mAxesThreeAndHalf, 3.5)
        certificateContent.pressingPadsFive = getPressingPads(mAxesFive, 5.0)
        certificateContent.pressingPadsSix = getPressingPads(mAxesSix, 6.0)
        certificateContent.pressingPadsSixAndHalf = getPressingPads(mAxesSixAndHalf, 6.5)
        certificateContent.pressingPadsSeven = getPressingPads(mAxesSeven, 7.0)
        certificateContent.pressingPadsSevenAndHalf = getPressingPads(mAxesSevenAndHalf, 7.5)
        certificateContent.pressingPadsEight = getPressingPads(mAxesEight, 8.0)
        certificateContent.pressingPadsEightAndHalf = getPressingPads(mAxesEightAndHalf, 8.5)
        certificateContent.pressingPadsNine = getPressingPads(mAxesNine, 9.0)
        certificateContent.pressingPadsTen = getPressingPads(mAxesTen, 10.0)
        certificateContent.pressingPadsTwelve = getPressingPads(mAxesTwelve, 12.0)
        certificateContent.pressingPadsFifteen = getPressingPads(mAxesFifteen, 15.0)
        certificateContent.totalAxes = mTotalAxes.toString()
        certificateContent.pressingPadsRequired = getPressingPadsRequired()
        certificateContent.handBrakesRequired = getHandBrakesRequired()
        return certificateContent
    }

    private fun getPressingPads(axesCount: Int, coefficient: Double): String {
        val pressingPads = axesCount * coefficient
        if (pressingPads - pressingPads.toInt() > 0) {
            pressingPads + 1
        }
        return when (pressingPads != 0.0) {
            true -> "${pressingPads.toInt()}"
            else -> ""
        }
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

    private fun initValues(certificate: CertificateContent) {
        mAxesTwoAndHalf = getValidInt(certificate.axesTwoAndHalf)
        mAxesThreeAndHalf = getValidInt(certificate.axesThreeAndHalf)
        mAxesFive = getValidInt(certificate.axesFive)
        mAxesSix = getValidInt(certificate.axesSix)
        mAxesSixAndHalf = getValidInt(certificate.axesSixAndHalf)
        mAxesSeven = getValidInt(certificate.axesSeven)
        mAxesSevenAndHalf = getValidInt(certificate.axesSevenAndHalf)
        mAxesEight = getValidInt(certificate.axesEight)
        mAxesEightAndHalf = getValidInt(certificate.axesEightAndHalf)
        mAxesNine = getValidInt(certificate.axesNine)
        mAxesTen = getValidInt(certificate.axesTen)
        mAxesTwelve = getValidInt(certificate.axesTwelve)
        mAxesFifteen = getValidInt(certificate.axesFifteen)
        mTotalAxes = mAxesTwoAndHalf +
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
        mWeight = getValidDouble(certificate.weight)
        mSlopeFactor = getValidDouble(certificate.slopeFactor)
        mCoefficient = when (certificate.isLoaded) {
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