package com.project.gamedb.data.source.remote.api

import android.net.Uri
import java.util.*

object ApiService {
    fun queryFeature() = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API_RAWG)
        .appendPath(ApiConstants.API)
        .appendPath(ApiConstants.GAMES)
        .appendQueryParameter(ApiConstants.KEY,ApiConstants.API_KEY)
        .toString()
}
