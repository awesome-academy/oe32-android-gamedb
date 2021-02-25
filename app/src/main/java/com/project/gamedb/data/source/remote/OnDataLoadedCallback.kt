package com.project.gamedb.data.source.remote

import com.project.gamedb.R
import java.lang.Exception

interface OnDataLoadedCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(exception: Exception? = Exception(R.string.err_unexpected_error.toString()))
}
