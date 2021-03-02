package com.project.gamedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Platform(
    val platformId: Long,
    val platformSlug: String,
    val platformName: String
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(PLATFORM_ID),
        jsonObject.getString(PLATFORM_SLUG),
        jsonObject.getString(PLATFORM_NAME)
    )

    companion object {
        const val PLATFORM_ID = "id"
        const val PLATFORM_SLUG = "slug"
        const val PLATFORM_NAME = "name"
    }
}
