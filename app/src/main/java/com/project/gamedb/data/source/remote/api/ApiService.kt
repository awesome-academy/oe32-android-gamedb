package com.project.gamedb.data.source.remote.api

import android.net.Uri
import java.util.*

object ApiService {
    fun queryFeature() = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API_RAWG)
        .appendPath(ApiConstants.API)
        .appendPath(ApiConstants.GAMES)
        .appendQueryParameter(ApiConstants.KEY, ApiConstants.API_KEY)
        .toString()

    fun queryFeatureOrdered(ordering: String) = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API_RAWG)
        .appendPath(ApiConstants.API)
        .appendPath(ApiConstants.GAMES)
        .appendQueryParameter(ApiConstants.KEY, ApiConstants.API_KEY)
        .appendQueryParameter(ApiConstants.ORDERING, ordering)
        .toString()

    fun queryGameDetail(id: Long) = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API_RAWG)
        .appendPath(ApiConstants.API)
        .appendPath(ApiConstants.GAMES)
        .appendPath(id.toString())
        .toString()

    fun queryGenres() = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API_RAWG)
        .appendPath(ApiConstants.API)
        .appendPath(ApiConstants.GENRES)
        .toString()

    fun queryRanking(year: Int) = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API_RAWG)
        .appendPath(ApiConstants.API)
        .appendPath(ApiConstants.GAMES)
        .appendQueryParameter(ApiConstants.KEY, ApiConstants.API_KEY)
        .appendQueryParameter(ApiConstants.DATES, getRankingTime(year))
        .toString().replace(ApiConstants.COMMA_CODE, ApiConstants.COMMA)

    private fun getRankingTime(year: Int): String = "$year-01-01,$year-12-12"
}
