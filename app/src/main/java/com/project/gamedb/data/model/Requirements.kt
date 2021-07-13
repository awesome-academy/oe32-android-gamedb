package com.project.gamedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Requirements(
    val minimum: String,
    val recommended: String
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        if (jsonObject.has(MINIMUM)) jsonObject.getString(MINIMUM) else "",
        if (jsonObject.has(RECOMMENDED)) jsonObject.getString(RECOMMENDED) else "",
    )

    companion object {
        const val MINIMUM = "minimum"
        const val RECOMMENDED = "recommended"
    }
}

