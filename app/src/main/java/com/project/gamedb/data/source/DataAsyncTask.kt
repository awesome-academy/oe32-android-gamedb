package com.project.gamedb.data.source

import android.os.AsyncTask
import com.project.gamedb.R
import com.project.gamedb.data.source.remote.OnDataLoadedCallback

class DataAsyncTask<T>(
    private val callback: OnDataLoadedCallback<T>,
    private val handle: () -> T
) : AsyncTask<String, Unit, T?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: String): T? =
        try {
            handle()
                ?: throw Exception(R.string.err_no_param_found.toString())
        } catch (e: Exception) {
            exception = e
            null
        }

    override fun onPostExecute(result: T?) {
        result?.let(callback::onSuccess) ?: (callback.onFailure(exception))
    }
}
