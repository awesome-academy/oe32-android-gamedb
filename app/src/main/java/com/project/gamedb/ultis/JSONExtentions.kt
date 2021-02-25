package com.project.gamedb.ultis

import org.json.JSONArray
import org.json.JSONObject

inline fun <R> JSONArray.map(transform : (JSONObject) -> R?) : List<R> =
    mutableListOf<R>().also { list ->
        repeat(length()){
            (get(it) as? JSONObject)?.let(transform)?.let(list::add)
        }
    }

fun JSONObject.toStringArray(array:JSONArray):List<String> =
    mutableListOf<String>().also {
        for (i in it.indices) {
            it[i] = array.optString(i)
        }
    }

