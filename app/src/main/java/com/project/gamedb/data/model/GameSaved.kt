package com.project.gamedb.data.model

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameSaved(
    val id: Int,
    val name: String,
    val genres: String,
    val url: String,
    val date: String,
    val platform: String,
    val score: Int,
    val reviewCount: String
) : Parcelable {

    constructor(cursor: Cursor) : this(
        id = cursor.getInt(cursor.getColumnIndex(ID)),
        name = cursor.getString(cursor.getColumnIndex(NAME)),
        genres = cursor.getString(cursor.getColumnIndex(GENRES)),
        url = cursor.getString(cursor.getColumnIndex(IMAGE_URL)),
        date = cursor.getString(cursor.getColumnIndex(RELEASED_DATE)),
        platform = cursor.getString(cursor.getColumnIndex(PLATFORM)),
        score = cursor.getInt(cursor.getColumnIndex(META_SCORE)),
        reviewCount = cursor.getString(cursor.getColumnIndex(REVIEW_COUNT))
    )

    fun getContentValue() = ContentValues().apply {
        put(ID, id)
        put(NAME, name)
        put(GENRES, genres)
        put(IMAGE_URL, url)
        put(RELEASED_DATE, date)
        put(PLATFORM, platform)
        put(META_SCORE, score)
        put(REVIEW_COUNT, reviewCount)
    }

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val GENRES = "genres"
        const val IMAGE_URL = "image_url"
        const val RELEASED_DATE = "released_date"
        const val PLATFORM = "platform"
        const val META_SCORE = "meta_score"
        const val REVIEW_COUNT = "review_count"
    }
}
