package org.vizhev.certificate.vu.fortyfive.data.api

//TODO implement calculation
class AppCalculator : Calculator {

    private var coefficient: Double = 0.0
    private var weight: Double = 0.0
    private var axles: Double = 0.0
    private var scotches: Double = 0.0
    private var isTrainEmpty: Boolean = false

    /*private var
    private var
    private var
    private var
    private var
    private var
    private var
    private var*/

    init {
        coefficient = when (isTrainEmpty) {
            true -> if (axles <= 350) 0.55 else 0.44
            else -> 0.33
        }
    }

    override fun getHolding(): String {
        var handbrakes: Double = ((weight / 100 * 0.8) - scotches) / 4
        if (handbrakes - handbrakes.toInt() > 0.0) {
            handbrakes++
        }
        return "BS - ${handbrakes.toInt()}HB"
    }

    override fun getEffort(): String {
        return ""
    }

    private fun calculateEffortNeeded(): Int {
        var effortNeeded: Double = weight * coefficient
        if (effortNeeded - effortNeeded.toInt() > 0.0) {
            effortNeeded++
        }
        return effortNeeded.toInt()
    }

    private fun calculateEffortFact(): Int {
        return 0
    }

    private fun convertToDouble(value: String): Double {
        return 0.0
    }
}