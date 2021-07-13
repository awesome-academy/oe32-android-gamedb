package com.project.gamedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
class GenresDetails(
    val id: Long,
    val name: String,
    val description: String
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(GENRES_ID),
        jsonObject.getString(GENRES_NAME),
        jsonObject.getString(GENRES_DESCRIPTION),
    )

    companion object {
        const val GENRES_ID = "id"
        const val GENRES_NAME = "name"
        const val GENRES_DESCRIPTION = "description"
    }
}
