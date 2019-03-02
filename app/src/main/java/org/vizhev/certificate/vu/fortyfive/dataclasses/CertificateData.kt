package org.vizhev.certificate.vu.fortyfive.dataclasses

import com.ironz.binaryprefs.serialization.serializer.persistable.Persistable
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataInput
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataOutput

data class CertificateData(
    //general
        var id: String = "",
        var date: String = "",
        var time: String = "",
        var locomotiveSeries: String = "",
        var trainNumber: String = "",
        var lastWagonNumber: String = "",
    //for calculate
        var isLoaded: Boolean = false,
        var weight: String = "",
        var slopeRatio: String = "",
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
        var padsRequired: String = "",
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
        locomotiveSeries = input.readString()
        trainNumber = input.readString()
        weight = input.readString()
        totalAxes = input.readString()
        lastWagonNumber = input.readString()
        weight = input.readString()
        totalAxes = input.readString()
        pressingPadsRequired = input.readString()
        slopeRatio = input.readString()
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
        padsRequired = input.readString()
        handBrakesRequired = input.readString()
    }

    override fun writeExternal(output: DataOutput?) {
        output!!.writeString(id)
        output.writeString(date)
        output.writeString(time)
        output.writeString(locomotiveSeries)
        output.writeString(trainNumber)
        output.writeString(weight)
        output.writeString(totalAxes)
        output.writeString(lastWagonNumber)
        output.writeString(weight)
        output.writeString(totalAxes)
        output.writeString(pressingPadsRequired)
        output.writeString(slopeRatio)
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
        output.writeString(padsRequired)
        output.writeString(handBrakesRequired)
    }

    override fun deepClone(): CertificateData {
        val certificateData = CertificateData()
        certificateData.id = id
        certificateData.date = date
        certificateData.time = time
        certificateData.locomotiveSeries = locomotiveSeries
        certificateData.trainNumber = trainNumber
        certificateData.weight = weight
        certificateData.totalAxes = totalAxes
        certificateData.lastWagonNumber = lastWagonNumber
        certificateData.weight = weight
        certificateData.totalAxes = totalAxes
        certificateData.pressingPadsRequired = pressingPadsRequired
        certificateData.slopeRatio = slopeRatio
        certificateData.pressingPadsTwoAndHalf = pressingPadsTwoAndHalf
        certificateData.pressingPadsThreeAndHalf = pressingPadsThreeAndHalf
        certificateData.pressingPadsFive = pressingPadsFive
        certificateData.pressingPadsSix = pressingPadsSix
        certificateData.pressingPadsSixAndHalf = pressingPadsSixAndHalf
        certificateData.pressingPadsSeven = pressingPadsSeven
        certificateData.pressingPadsSevenAndHalf = pressingPadsSevenAndHalf
        certificateData.pressingPadsEight = pressingPadsEight
        certificateData.pressingPadsEightAndHalf = pressingPadsEightAndHalf
        certificateData.pressingPadsNine = pressingPadsNine
        certificateData.pressingPadsTen = pressingPadsTen
        certificateData.pressingPadsTwelve = pressingPadsTwelve
        certificateData.pressingPadsFifteen = pressingPadsFifteen
        certificateData.padsRequired = padsRequired
        certificateData.handBrakesRequired = handBrakesRequired
        return certificateData
    }
}