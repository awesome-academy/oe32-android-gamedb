package com.project.gamedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class ESRBRating(
    val ratingId: Long,
    val ratingSlug: String,
    val ratingName: String
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(RATING_ID),
        jsonObject.getString(RATING_SLUG),
        jsonObject.getString(RATING_NAME)
    )

    companion object {
        const val RATING_ID = "id"
        const val RATING_SLUG = "slug"
        const val RATING_NAME = "name"
    }
}
