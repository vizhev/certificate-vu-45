package org.vizhev.certificate.vu.fortyfive.dataclasses

import com.ironz.binaryprefs.serialization.serializer.persistable.Persistable
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataInput
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataOutput

data class Parameters(
        var id: String = "",
        var date: String = "",
        var locomotiveSeries: String = "",
        var trainNumber: String = "",
        var tailWagonNumber: String = "",
        var weight: String = "",
        var totalAxes: String = "",
        var pressingPads: String = ""
) : Persistable {

    companion object {
        const val KEY: String = "Parameters"
    }

    override fun readExternal(input: DataInput?) {
        id = input!!.toString()
        date = input.readString()
        locomotiveSeries = input.readString()
        trainNumber = input.readString()
        weight = input.readString()
        totalAxes = input.readString()
        tailWagonNumber = input.readString()
    }

    override fun writeExternal(output: DataOutput?) {
        output!!.writeString(id)
        output.writeString(date)
        output.writeString(locomotiveSeries)
        output.writeString(trainNumber)
        output.writeString(weight)
        output.writeString(totalAxes)
        output.writeString(tailWagonNumber)
    }

    override fun deepClone(): Parameters {
        val parameters = Parameters()
        parameters.date = date
        parameters.locomotiveSeries = locomotiveSeries
        parameters.trainNumber = trainNumber
        parameters.weight = weight
        parameters.totalAxes = totalAxes
        parameters.tailWagonNumber = tailWagonNumber
        return parameters
    }

}