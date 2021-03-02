package com.project.gamedb.data.model

import android.os.Parcelable
import com.project.gamedb.ultis.map
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Games(
    val gameId: Long,
    val gameSlug: String,
    val gameName: String,
    val gameReleased: String,
    val gameTba: Boolean,
    val gameBackgroundImage: String,
    val gameRating: Long,
    val gameRatingTop: Long,
    val gameRatingsCount: Long,
    val gameReviewsCounts: String,
    val gameAdded: Long,
    val gameGenres: List<Genres>,
    val gameMetaCritic: Long,
    val gamePlaytime: Long,
    val gameSuggestionsCount: Long,
    val gameUpdated: String,
    val gameEsrbRating: ESRBRating?,
    val gamePlatforms: List<PlatformByGame>
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(GAME_ID),
        jsonObject.getString(GAME_SLUG),
        jsonObject.getString(GAME_NAME),
        jsonObject.getString(GAME_RELEASED),
        jsonObject.getBoolean(GAME_TBA),
        jsonObject.getString(GAME_BACKGROUND_IMAGE),
        jsonObject.getLong(GAME_RATING),
        jsonObject.getLong(GAME_RATING_TOP),
        jsonObject.getLong(GAME_RATING_COUNT),
        jsonObject.getString(GAME_REVIEWS_COUNT),
        jsonObject.getLong(GAME_ADDED),
        jsonObject.getJSONArray(GAME_GENRES).map(::Genres),
        if (jsonObject.getString(GAME_META_CRITIC) != "null") jsonObject.getLong(GAME_META_CRITIC) else -1,
        jsonObject.getLong(GAME_PLAY_TIME),
        jsonObject.getLong(GAME_SUGGESTION_COUNT),
        jsonObject.getString(GAME_UPDATED),
        if (jsonObject.getString(GAME_ESRB_RATING) != "null") {
            jsonObject.getJSONObject(GAME_ESRB_RATING).let(::ESRBRating)
        } else {
            null
        },
        jsonObject.getJSONArray(GAME_PLATFORM).map(::PlatformByGame)
    )

    companion object {
        const val GAME_ID = "id"
        const val GAME_SLUG = "slug"
        const val GAME_NAME = "name"
        const val GAME_RELEASED = "released"
        const val GAME_TBA = "tba"
        const val GAME_BACKGROUND_IMAGE = "background_image"
        const val GAME_RATING = "rating"
        const val GAME_RATING_TOP = "rating_top"
        const val GAME_RATING_COUNT = "ratings_count"
        const val GAME_REVIEWS_COUNT = "reviews_count"
        const val GAME_ADDED = "added"
        const val GAME_META_CRITIC = "metacritic"
        const val GAME_PLAY_TIME = "playtime"
        const val GAME_SUGGESTION_COUNT = "suggestions_count"
        const val GAME_UPDATED = "updated"
        const val GAME_ESRB_RATING = "esrb_rating"
        const val GAME_PLATFORM = "platforms"
        const val GAME_GENRES = "genres"
    }
}
