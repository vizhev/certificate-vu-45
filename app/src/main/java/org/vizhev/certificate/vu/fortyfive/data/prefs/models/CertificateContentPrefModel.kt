package org.vizhev.certificate.vu.fortyfive.data.prefs.models

import com.ironz.binaryprefs.serialization.serializer.persistable.Persistable
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataInput
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataOutput

class CertificateContentPrefModel(
    var id: Long = 0,
    var date: String = "",
    var issueTime: String = "",
    var stationStamp: String = "",
    var locomotiveSeries: String = "",
    var trainNumber: String = "",
    var lastWagonNumber: String = "",
    var isLoaded: Boolean = false,
    var weight: String = "",
    var slopeFactor: String = "",
    var axesTwoAndHalf: String = "",
    var axesThreeAndHalf: String = "",
    var axesFive: String = "",
    var axesSix: String = "",
    var axesSixAndHalf: String = "",
    var axesSeven: String = "",
    var axesSevenAndHalf: String = "",
    var axesEight: String = "",
    var axesEightAndHalf: String = "",
    var axesNine: String = "",
    var axesTen: String = "",
    var axesTwelve: String = "",
    var axesFifteen: String = "",
    var pressingPadsTwoAndHalf: String = "",
    var pressingPadsThreeAndHalf: String = "",
    var pressingPadsFive: String = "",
    var pressingPadsSix: String = "",
    var pressingPadsSixAndHalf: String = "",
    var pressingPadsSeven: String = "",
    var pressingPadsSevenAndHalf: String = "",
    var pressingPadsEight: String = "",
    var pressingPadsEightAndHalf: String = "",
    var pressingPadsNine: String = "",
    var pressingPadsTen: String = "",
    var pressingPadsTwelve: String = "",
    var pressingPadsFifteen: String = "",
    var totalAxes: String = "",
    var pressingPadsRequired: String = "",
    var handBrakesRequired: String = "",
    var handBrakeAxes: String = "",
    var brakeNetworkDensity: String = ""
) : Persistable {

    override fun readExternal(p0: DataInput?) {
        p0?.let {
            id = it.readLong()
            date = it.readString()
            issueTime = it.readString()
            stationStamp = it.readString()
            locomotiveSeries = it.readString()
            trainNumber = it.readString()
            lastWagonNumber = it.readString()
            isLoaded = it.readBoolean()
            weight = it.readString()
            slopeFactor = it.readString()
            axesTwoAndHalf = it.readString()
            axesThreeAndHalf = it.readString()
            axesFive = it.readString()
            axesSix = it.readString()
            axesSixAndHalf = it.readString()
            axesSeven = it.readString()
            axesSevenAndHalf = it.readString()
            axesEight = it.readString()
            axesEightAndHalf = it.readString()
            axesNine = it.readString()
            axesTen = it.readString()
            axesTwelve = it.readString()
            axesFifteen = it.readString()
            pressingPadsTwoAndHalf = it.readString()
            pressingPadsThreeAndHalf = it.readString()
            pressingPadsFive = it.readString()
            pressingPadsSix = it.readString()
            pressingPadsSixAndHalf = it.readString()
            pressingPadsSeven = it.readString()
            pressingPadsSevenAndHalf = it.readString()
            pressingPadsEight = it.readString()
            pressingPadsEightAndHalf = it.readString()
            pressingPadsNine = it.readString()
            pressingPadsTen = it.readString()
            pressingPadsTwelve = it.readString()
            pressingPadsFifteen = it.readString()
            totalAxes = it.readString()
            pressingPadsRequired = it.readString()
            handBrakesRequired = it.readString()
            handBrakeAxes = it.readString()
            brakeNetworkDensity = it.readString()
        }
    }

    override fun writeExternal(p0: DataOutput?) {
        p0?.let {
            it.writeLong(id)
            it.writeString(date)
            it.writeString(issueTime)
            it.writeString(stationStamp)
            it.writeString(locomotiveSeries)
            it.writeString(trainNumber)
            it.writeString(lastWagonNumber)
            it.writeBoolean(isLoaded)
            it.writeString(weight)
            it.writeString(slopeFactor)
            it.writeString(axesTwoAndHalf)
            it.writeString(axesThreeAndHalf)
            it.writeString(axesFive)
            it.writeString(axesSix)
            it.writeString(axesSixAndHalf)
            it.writeString(axesSeven)
            it.writeString(axesSevenAndHalf)
            it.writeString(axesEight)
            it.writeString(axesEightAndHalf)
            it.writeString(axesNine)
            it.writeString(axesTen)
            it.writeString(axesTwelve)
            it.writeString(axesFifteen)
            it.writeString(pressingPadsTwoAndHalf)
            it.writeString(pressingPadsThreeAndHalf)
            it.writeString(pressingPadsFive)
            it.writeString(pressingPadsSix)
            it.writeString(pressingPadsSixAndHalf)
            it.writeString(pressingPadsSeven)
            it.writeString(pressingPadsSevenAndHalf)
            it.writeString(pressingPadsEight)
            it.writeString(pressingPadsEightAndHalf)
            it.writeString(pressingPadsNine)
            it.writeString(pressingPadsTen)
            it.writeString(pressingPadsTwelve)
            it.writeString(pressingPadsFifteen)
            it.writeString(totalAxes)
            it.writeString(pressingPadsRequired)
            it.writeString(handBrakesRequired)
            it.writeString(handBrakeAxes)
            it.writeString(brakeNetworkDensity)
        }
    }

    override fun deepClone() = CertificateContentPrefModel(
        id = id,
        date = date,
        issueTime = issueTime,
        stationStamp = stationStamp,
        locomotiveSeries = locomotiveSeries,
        trainNumber = trainNumber,
        lastWagonNumber = lastWagonNumber,
        isLoaded = isLoaded,
        weight = weight,
        slopeFactor = slopeFactor,
        axesTwoAndHalf = axesTwoAndHalf,
        axesThreeAndHalf = axesThreeAndHalf,
        axesFive = axesFive,
        axesSix = axesSix,
        axesSixAndHalf = axesSixAndHalf,
        axesSeven = axesSeven,
        axesSevenAndHalf = axesSevenAndHalf,
        axesEight = axesEight,
        axesEightAndHalf = axesEightAndHalf,
        axesNine = axesNine,
        axesTen = axesTen,
        axesTwelve = axesTwelve,
        axesFifteen = axesFifteen,
        pressingPadsTwoAndHalf = pressingPadsTwoAndHalf,
        pressingPadsThreeAndHalf = pressingPadsThreeAndHalf,
        pressingPadsFive = pressingPadsFive,
        pressingPadsSix = pressingPadsSix,
        pressingPadsSixAndHalf = pressingPadsSixAndHalf,
        pressingPadsSeven = pressingPadsSeven,
        pressingPadsSevenAndHalf = pressingPadsSevenAndHalf,
        pressingPadsEight = pressingPadsEight,
        pressingPadsEightAndHalf = pressingPadsEightAndHalf,
        pressingPadsNine = pressingPadsNine,
        pressingPadsTen = pressingPadsTen,
        pressingPadsTwelve = pressingPadsTwelve,
        pressingPadsFifteen = pressingPadsFifteen,
        totalAxes = totalAxes,
        pressingPadsRequired = pressingPadsRequired,
        handBrakesRequired = handBrakesRequired,
        handBrakeAxes = handBrakeAxes,
        brakeNetworkDensity = brakeNetworkDensity
    )
}