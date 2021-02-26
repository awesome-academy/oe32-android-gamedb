package com.project.gamedb.data.source.remote

import com.project.gamedb.data.source.GameDataSource
import com.project.gamedb.data.source.remote.api.ApiConstants
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class GameRemoteDataSource : GameDataSource.Remote {
    private fun makeNetworkCall(
        url: URL,
        method: String = ApiConstants.METHOD_GET
    ): String {
        var urlConnection: HttpURLConnection? = null
        return try {
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.apply {
                doInput = true
                requestMethod = method
                connect()
            }
            val respondCode = urlConnection.responseCode
            if (respondCode == HttpURLConnection.HTTP_OK) {
                url.openStream().bufferedReader().use(BufferedReader::readText)
            } else throw IOException(urlConnection.responseMessage)
        } finally {
            urlConnection?.disconnect()
        }
    }
    companion object {
        private var instance: GameRemoteDataSource? = null

        fun getInstance() = instance ?: GameRemoteDataSource().also { instance = it }
    }
}
