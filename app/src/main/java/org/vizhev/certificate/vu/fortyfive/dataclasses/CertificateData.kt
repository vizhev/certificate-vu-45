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
    var pressingPads: String = "",
    var slopeRatio: String = "",
    var brakePressureTwoAndHalf: String = "",
    var brakePressureThreeAndHalf: String = "",
    var brakePressureFive: String = "",
    var brakePressureSix: String = "",
    var brakePressureSixAndHalf: String = "",
    var brakePressureSeven: String = "",
    var brakePressureSevenAndHalf: String = "",
    var brakePressureEight: String = "",
    var brakePressureEightAndHalf: String = "",
    var brakePressureNine: String = "",
    var brakePressureTen: String = "",
    var brakePressureTwelve: String = "",
    var brakePressureFifteen: String = "",
    //for result
    var totalAxes: String = "",
    var padsRequired: String = "",
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
        pressingPads = input.readString()
        slopeRatio = input.readString()
        brakePressureTwoAndHalf = input.readString()
        brakePressureThreeAndHalf = input.readString()
        brakePressureFive = input.readString()
        brakePressureSix = input.readString()
        brakePressureSixAndHalf = input.readString()
        brakePressureSeven = input.readString()
        brakePressureSevenAndHalf = input.readString()
        brakePressureEight = input.readString()
        brakePressureEightAndHalf = input.readString()
        brakePressureNine = input.readString()
        brakePressureTen = input.readString()
        brakePressureTwelve = input.readString()
        brakePressureFifteen = input.readString()
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
        output.writeString(pressingPads)
        output.writeString(slopeRatio)
        output.writeString(brakePressureTwoAndHalf)
        output.writeString(brakePressureThreeAndHalf)
        output.writeString(brakePressureFive)
        output.writeString(brakePressureSix)
        output.writeString(brakePressureSixAndHalf)
        output.writeString(brakePressureSeven)
        output.writeString(brakePressureSevenAndHalf)
        output.writeString(brakePressureEight)
        output.writeString(brakePressureEightAndHalf)
        output.writeString(brakePressureNine)
        output.writeString(brakePressureTen)
        output.writeString(brakePressureTwelve)
        output.writeString(brakePressureFifteen)
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
        certificateData.pressingPads = pressingPads
        certificateData.slopeRatio = slopeRatio
        certificateData.brakePressureTwoAndHalf = brakePressureTwoAndHalf
        certificateData.brakePressureThreeAndHalf = brakePressureThreeAndHalf
        certificateData.brakePressureFive = brakePressureFive
        certificateData.brakePressureSix = brakePressureSix
        certificateData.brakePressureSixAndHalf = brakePressureSixAndHalf
        certificateData.brakePressureSeven = brakePressureSeven
        certificateData.brakePressureSevenAndHalf = brakePressureSevenAndHalf
        certificateData.brakePressureEight = brakePressureEight
        certificateData.brakePressureEightAndHalf = brakePressureEightAndHalf
        certificateData.brakePressureNine = brakePressureNine
        certificateData.brakePressureTen = brakePressureTen
        certificateData.brakePressureTwelve = brakePressureTwelve
        certificateData.brakePressureFifteen = brakePressureFifteen
        certificateData.padsRequired = padsRequired
        certificateData.handBrakesRequired = handBrakesRequired
        return certificateData
    }
}