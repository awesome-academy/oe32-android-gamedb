package com.project.gamedb.data.model

import android.os.Parcelable
import com.project.gamedb.ultis.map
import com.project.gamedb.ultis.toStringArray
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
class GameDetail(
    val id: Long,
    val slug: String,
    val name: String,
    val description: String,
    val released: String,
    val updated: String,
    val backgroundImage: String,
    val backgroundImageAdditional: String,
    val website: String,
    val rating: Long,
    val added: Long,
    val playtime: Long,
    val creatorsCount: Long,
    val achievementsCount: Long,
    val reviewsTextCount: String,
    val ratingsCount: Long,
    val suggestionsCount: Long,
    val alternativeNames: List<String>,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(ID),
        jsonObject.getString(SLUG),
        jsonObject.getString(NAME),
        jsonObject.getString(DESCRIPTION),
        jsonObject.getString(RELEASED),
        jsonObject.getString(UPDATED),
        jsonObject.getString(BACKGROUND_IMAGE),
        jsonObject.getString(BACKGROUND_IMAGE_ADDITIONAL),
        jsonObject.getString(WEBSITE),
        jsonObject.getLong(RATING),
        jsonObject.getLong(ADDED),
        jsonObject.getLong(PLAY_TIME),
        jsonObject.getLong(CREATORS_COUNT),
        jsonObject.getLong(ACHIEVEMENTS_COUNT),
        jsonObject.getString(REVIEWS_TEXT_COUNT),
        jsonObject.getLong(RATINGS_COUNT),
        jsonObject.getLong(SUGGESTION_COUNT),
        jsonObject.toStringArray(jsonObject.getJSONArray(ALTERNATE_NAME))
    )

    companion object {
        const val ID = "id"
        const val SLUG = "slug"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val RELEASED = "released"
        const val UPDATED = "updated"
        const val BACKGROUND_IMAGE = "background_image"
        const val BACKGROUND_IMAGE_ADDITIONAL = "background_image_additional"
        const val WEBSITE = "website"
        const val RATING = "rating"
        const val ADDED = "added"
        const val PLAY_TIME = "playtime"
        const val CREATORS_COUNT = "creators_count"
        const val ACHIEVEMENTS_COUNT = "achievements_count"
        const val REVIEWS_TEXT_COUNT = "reviews_text_count"
        const val RATINGS_COUNT = "ratings_count"
        const val SUGGESTION_COUNT = "suggestions_count"
        const val ALTERNATE_NAME = "alternative_names"
    }
}
