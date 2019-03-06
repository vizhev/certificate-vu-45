package org.vizhev.certificate.vu.fortyfive.dataclasses

import com.ironz.binaryprefs.serialization.serializer.persistable.Persistable
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataInput
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataOutput

data class CertificateData(
    //general
        var id: String = "",
        var date: String = "",
        var time: String = "",
        var stationStamp: String = "",
        var locomotiveSeries: String = "",
        var trainNumber: String = "",
        var lastWagonNumber: String = "",
    //for calculate
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
    //for result
        var totalAxes: String = "",
        var pressingPadsRequired: String = "",
        var handBrakesRequired: String = ""
) : Persistable {

    companion object {
        const val KEY: String = "Parameters"
    }

    override fun readExternal(input: DataInput?) {
        id = input!!.readString()
        date = input.readString()
        time = input.readString()
        stationStamp = input.readString()
        locomotiveSeries = input.readString()
        trainNumber = input.readString()
        lastWagonNumber = input.readString()
        isLoaded = input.readBoolean()
        weight = input.readString()
        slopeFactor = input.readString()
        handsBrakesFact = input.readString()
        axesTwoAndHalf = input.readString()
        axesThreeAndHalf = input.readString()
        axesFive = input.readString()
        axesSix = input.readString()
        axesSixAndHalf = input.readString()
        axesSeven = input.readString()
        axesSevenAndHalf = input.readString()
        axesEight = input.readString()
        axesEightAndHalf = input.readString()
        axesNine = input.readString()
        axesTen = input.readString()
        axesTwelve = input.readString()
        axesFifteen = input.readString()
        pressingPadsTwoAndHalf = input.readString()
        pressingPadsThreeAndHalf = input.readString()
        pressingPadsFive = input.readString()
        pressingPadsSix = input.readString()
        pressingPadsSixAndHalf = input.readString()
        pressingPadsSeven = input.readString()
        pressingPadsSevenAndHalf = input.readString()
        pressingPadsEight = input.readString()
        pressingPadsEightAndHalf = input.readString()
        pressingPadsNine = input.readString()
        pressingPadsTen = input.readString()
        pressingPadsTwelve = input.readString()
        pressingPadsFifteen = input.readString()
        totalAxes = input.readString()
        pressingPadsRequired = input.readString()
        handBrakesRequired =input.readString()
    }

    override fun writeExternal(output: DataOutput?) {
        output!!.writeString(id)
          output.writeString(date)
          output.writeString(time)
          output.writeString(stationStamp)
          output.writeString(locomotiveSeries)
          output.writeString(trainNumber)
          output.writeString(lastWagonNumber)
          output.writeBoolean(isLoaded)
          output.writeString(weight)
          output.writeString(slopeFactor)
          output.writeString(handsBrakesFact)
          output.writeString(axesTwoAndHalf)
          output.writeString(axesThreeAndHalf)
          output.writeString(axesFive)
          output.writeString(axesSix)
          output.writeString(axesSixAndHalf)
          output.writeString(axesSeven)
          output.writeString(axesSevenAndHalf)
          output.writeString(axesEight)
          output.writeString(axesEightAndHalf)
          output.writeString(axesNine)
          output.writeString(axesTen)
          output.writeString(axesTwelve)
          output.writeString(axesFifteen)
          output.writeString(pressingPadsTwoAndHalf)
          output.writeString(pressingPadsThreeAndHalf)
          output.writeString(pressingPadsFive)
          output.writeString(pressingPadsSix)
          output.writeString(pressingPadsSixAndHalf)
          output.writeString(pressingPadsSeven)
          output.writeString(pressingPadsSevenAndHalf)
          output.writeString(pressingPadsEight)
          output.writeString(pressingPadsEightAndHalf)
          output.writeString(pressingPadsNine)
          output.writeString(pressingPadsTen)
          output.writeString(pressingPadsTwelve)
          output.writeString(pressingPadsFifteen)
          output.writeString(totalAxes)
          output.writeString(pressingPadsRequired)
          output.writeString(handBrakesRequired)
    }

    override fun deepClone(): CertificateData {
        val certificateData = CertificateData()
        certificateData.id = id
        certificateData.date = date
        certificateData.time = time
        certificateData.stationStamp = stationStamp
        certificateData.locomotiveSeries = locomotiveSeries
        certificateData.trainNumber = trainNumber
        certificateData.lastWagonNumber = lastWagonNumber
        certificateData.isLoaded = isLoaded
        certificateData.weight = weight
        certificateData.slopeFactor = slopeFactor
        certificateData.handsBrakesFact = handsBrakesFact
        certificateData.axesTwoAndHalf = axesTwoAndHalf
        certificateData.axesThreeAndHalf = axesThreeAndHalf
        certificateData.axesFive = axesFive
        certificateData.axesSix = axesSix
        certificateData.axesSixAndHalf = axesSixAndHalf
        certificateData.axesSeven = axesSeven
        certificateData.axesSevenAndHalf = axesSevenAndHalf
        certificateData.axesEight = axesEight
        certificateData.axesEightAndHalf = axesEightAndHalf
        certificateData.axesNine = axesNine
        certificateData.axesTen = axesTen
        certificateData.axesTwelve = axesTwelve
        certificateData.axesFifteen = axesFifteen
        certificateData.pressingPadsTwoAndHalf = pressingPadsTwoAndHalf
        certificateData.pressingPadsThreeAndHalf = pressingPadsThreeAndHalf
        return certificateData
    }
}