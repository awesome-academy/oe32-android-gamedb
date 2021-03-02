package com.project.gamedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Genres(
    val genresId: Long,
    val genresName: String,
    val genresSlug: String,
    val genresGamesCount: Long,
    val genresImageBackground: String
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(GENRES_ID),
        jsonObject.getString(GENRES_NAME),
        jsonObject.getString(GENRES_SLUG),
        jsonObject.getLong(GENRES_GAME_COUNT),
        jsonObject.getString(GENRES_IMAGE_BACKGROUND)
    )

    companion object {
        const val GENRES_ID = "id"
        const val GENRES_NAME = "name"
        const val GENRES_SLUG = "slug"
        const val GENRES_GAME_COUNT = "games_count"
        const val GENRES_IMAGE_BACKGROUND = "image_background"
    }
}
