package org.vizhev.certificate.vu.fortyfive.dataclasses

import com.ironz.binaryprefs.serialization.serializer.persistable.Persistable
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataInput
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataOutput

data class CertificateContent(
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
    var handsBrakesFact: String = "",
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
    var handBrakesRequired: String = ""
) : Persistable {

    companion object {
        const val KEY: String = "certificate"
    }

    override fun readExternal(p0: DataInput?) {
        id = p0!!.readLong()
        date = p0.readString()
        issueTime = p0.readString()
        stationStamp = p0.readString()
        locomotiveSeries = p0.readString()
        trainNumber = p0.readString()
        lastWagonNumber = p0.readString()
        isLoaded = p0.readBoolean()
        weight = p0.readString()
        slopeFactor = p0.readString()
        handsBrakesFact = p0.readString()
        axesTwoAndHalf = p0.readString()
        axesThreeAndHalf = p0.readString()
        axesFive = p0.readString()
        axesSix = p0.readString()
        axesSixAndHalf = p0.readString()
        axesSeven = p0.readString()
        axesSevenAndHalf = p0.readString()
        axesEight = p0.readString()
        axesEightAndHalf = p0.readString()
        axesNine = p0.readString()
        axesTen = p0.readString()
        axesTwelve = p0.readString()
        axesFifteen = p0.readString()
        pressingPadsTwoAndHalf = p0.readString()
        pressingPadsThreeAndHalf = p0.readString()
        pressingPadsFive = p0.readString()
        pressingPadsSix = p0.readString()
        pressingPadsSixAndHalf = p0.readString()
        pressingPadsSeven = p0.readString()
        pressingPadsSevenAndHalf = p0.readString()
        pressingPadsEight = p0.readString()
        pressingPadsEightAndHalf = p0.readString()
        pressingPadsNine = p0.readString()
        pressingPadsTen = p0.readString()
        pressingPadsTwelve = p0.readString()
        pressingPadsFifteen = p0.readString()
        totalAxes = p0.readString()
        pressingPadsRequired = p0.readString()
        handBrakesRequired = p0.readString()
    }

    override fun writeExternal(p0: DataOutput?) {
        p0!!.writeLong(id)
        p0.writeString(date)
        p0.writeString(issueTime)
        p0.writeString(stationStamp)
        p0.writeString(locomotiveSeries)
        p0.writeString(trainNumber)
        p0.writeString(lastWagonNumber)
        p0.writeBoolean(isLoaded)
        p0.writeString(weight)
        p0.writeString(slopeFactor)
        p0.writeString(handsBrakesFact)
        p0.writeString(axesTwoAndHalf)
        p0.writeString(axesThreeAndHalf)
        p0.writeString(axesFive)
        p0.writeString(axesSix)
        p0.writeString(axesSixAndHalf)
        p0.writeString(axesSeven)
        p0.writeString(axesSevenAndHalf)
        p0.writeString(axesEight)
        p0.writeString(axesEightAndHalf)
        p0.writeString(axesNine)
        p0.writeString(axesTen)
        p0.writeString(axesTwelve)
        p0.writeString(axesFifteen)
        p0.writeString(pressingPadsTwoAndHalf)
        p0.writeString(pressingPadsThreeAndHalf)
        p0.writeString(pressingPadsFive)
        p0.writeString(pressingPadsSix)
        p0.writeString(pressingPadsSixAndHalf)
        p0.writeString(pressingPadsSeven)
        p0.writeString(pressingPadsSevenAndHalf)
        p0.writeString(pressingPadsEight)
        p0.writeString(pressingPadsEightAndHalf)
        p0.writeString(pressingPadsNine)
        p0.writeString(pressingPadsTen)
        p0.writeString(pressingPadsTwelve)
        p0.writeString(pressingPadsFifteen)
        p0.writeString(totalAxes)
        p0.writeString(pressingPadsRequired)
        p0.writeString(handBrakesRequired)
    }

    override fun deepClone(): CertificateContent {
        val certificate = CertificateContent()
        certificate.id = id
        certificate.date = date
        certificate.issueTime = issueTime
        certificate.stationStamp = stationStamp
        certificate.locomotiveSeries = locomotiveSeries
        certificate.trainNumber = trainNumber
        certificate.lastWagonNumber = lastWagonNumber
        certificate.isLoaded = isLoaded
        certificate.weight = weight
        certificate.slopeFactor = slopeFactor
        certificate.handsBrakesFact = handsBrakesFact
        certificate.axesTwoAndHalf = axesTwoAndHalf
        certificate.axesThreeAndHalf = axesThreeAndHalf
        certificate.axesFive = axesFive
        certificate.axesSix = axesSix
        certificate.axesSixAndHalf = axesSixAndHalf
        certificate.axesSeven = axesSeven
        certificate.axesSevenAndHalf = axesSevenAndHalf
        certificate.axesEight = axesEight
        certificate.axesEightAndHalf = axesEightAndHalf
        certificate.axesNine = axesNine
        certificate.axesTen = axesTen
        certificate.axesTwelve = axesTwelve
        certificate.axesFifteen = axesFifteen
        certificate.pressingPadsTwoAndHalf = pressingPadsTwoAndHalf
        certificate.pressingPadsThreeAndHalf = pressingPadsThreeAndHalf
        certificate.pressingPadsFive = pressingPadsFive
        certificate.pressingPadsSix = pressingPadsSix
        certificate.pressingPadsSixAndHalf = pressingPadsSixAndHalf
        certificate.pressingPadsSeven = pressingPadsSeven
        certificate.pressingPadsSevenAndHalf = pressingPadsSevenAndHalf
        certificate.pressingPadsEight = pressingPadsEight
        certificate.pressingPadsEightAndHalf = pressingPadsEightAndHalf
        certificate.pressingPadsNine = pressingPadsNine
        certificate.pressingPadsTen = pressingPadsTen
        certificate.pressingPadsTwelve = pressingPadsTwelve
        certificate.pressingPadsFifteen = pressingPadsFifteen
        certificate.totalAxes = totalAxes
        certificate.pressingPadsTwoAndHalf = pressingPadsTwoAndHalf
        certificate.pressingPadsThreeAndHalf = pressingPadsThreeAndHalf
        return certificate
    }
}