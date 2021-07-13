package com.project.gamedb.data.model

import com.project.gamedb.ultis.map
import org.json.JSONObject

class ResultGenres (
    val count: Long,
    val next: String,
    val previous: String,
    val results: List<Genres>
) {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(COUNT),
        jsonObject.getString(NEXT),
        jsonObject.getString(PREVIOUS),
        jsonObject.getJSONArray(RESULTS).map(::Genres)
    )

    companion object {
        const val COUNT = "count"
        const val NEXT = "next"
        const val PREVIOUS = "previous"
        const val RESULTS = "results"
    }
}
