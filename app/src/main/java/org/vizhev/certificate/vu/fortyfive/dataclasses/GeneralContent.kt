package org.vizhev.certificate.vu.fortyfive.dataclasses

import com.ironz.binaryprefs.serialization.serializer.persistable.Persistable
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataInput
import com.ironz.binaryprefs.serialization.serializer.persistable.io.DataOutput

data class GeneralContent(
    var date: String,
    var locomotiveSeries: String

    ) : Persistable {

    companion object {
        const val KEY: String = "GeneralItemContent"
    }

    override fun readExternal(input: DataInput?) {
        date = input!!.readString()
    }

    override fun deepClone(): Persistable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun writeExternal(output: DataOutput?) {
        output!!.writeString(date)
        output.writeString(locomotiveSeries)
    }
}