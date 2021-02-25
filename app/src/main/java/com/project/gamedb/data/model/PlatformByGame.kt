package com.project.gamedb.data.model

import android.os.Parcelable
import org.json.JSONObject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlatformByGame(
    val platforms: Platform,
    val releasedAt: String,
    val requirements: Requirements?
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getJSONObject(GAME_ESRB_RATING).let(::Platform),
        jsonObject.getString(GAME_RELEASED_AT),
        if (jsonObject.getString(GAME_REQUIREMENT) != "null") {
            jsonObject.getJSONObject(GAME_REQUIREMENT).let(::Requirements)
        } else {
            null
        }
    )

    companion object {
        const val GAME_ESRB_RATING = "platform"
        const val GAME_RELEASED_AT = "released_at"
        const val GAME_REQUIREMENT = "requirements_en"
    }
}
