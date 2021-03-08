package com.project.gamedb.data.model

import android.os.Parcelable
import com.project.gamedb.ultis.map
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
class GameDetail(
    val id: Long,
    val name: String,
    val description: String,
    val released: String,
    val updated: String,
    val metacritic: Int,
    val ratingCount: Int,
    val backgroundImage: String,
    val backgroundImageAdditional: String,
    val rating: Long,
    val playtime: Long,
    val platforms: List<PlatformByGame>
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(ID),
        jsonObject.getString(NAME),
        jsonObject.getString(DESCRIPTION),
        jsonObject.getString(RELEASED),
        jsonObject.getString(UPDATED),
        if (jsonObject.getString(Games.GAME_META_CRITIC) != "null") jsonObject.getInt(Games.GAME_META_CRITIC) else 0,
        jsonObject.getInt(RATING_COUNT),
        jsonObject.getString(BACKGROUND_IMAGE),
        jsonObject.getString(BACKGROUND_IMAGE_ADDITIONAL),
        jsonObject.getLong(RATING),
        jsonObject.getLong(PLAY_TIME),
        jsonObject.getJSONArray(GAME_PLATFORM).map(::PlatformByGame)
    )

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val RELEASED = "released"
        const val UPDATED = "updated"
        const val METACRITIC = "metacritic"
        const val RATING_COUNT = "ratings_count"
        const val BACKGROUND_IMAGE = "background_image"
        const val BACKGROUND_IMAGE_ADDITIONAL = "background_image_additional"
        const val RATING = "rating"
        const val PLAY_TIME = "playtime"
        const val GAME_PLATFORM = "platforms"
    }
}
