package com.project.gamedb.data.source.remote

import com.project.gamedb.data.model.GameDetail
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.DataAsyncTask
import com.project.gamedb.data.source.GameDataSource
import com.project.gamedb.data.source.remote.api.ApiConstants
import com.project.gamedb.data.source.remote.api.ApiService
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class GameRemoteDataSource : GameDataSource.Remote {
    override fun getGames(callback: OnDataLoadedCallback<ResultGames>) {
        DataAsyncTask(callback) {
            getGames()
        }.execute("")
    }

    override fun getGamesOrdered(ordering: String, callback: OnDataLoadedCallback<ResultGames>) {
        DataAsyncTask(callback) {
            getGamesOrdered(it)
        }.execute(ordering)
    }

    override fun getGameDetail(id: Long, callback: OnDataLoadedCallback<GameDetail>) {
        DataAsyncTask(callback) {
            getGameDetail(it.toLong())
        }.execute(id.toString())
    }

    private fun getGameDetail(id: Long): GameDetail =
        JSONObject(makeNetworkCall((URL(ApiService.queryGameDetail(id))))).let(::GameDetail)

    private fun getGamesOrdered(ordering: String): ResultGames =
        JSONObject(makeNetworkCall((URL(ApiService.queryFeatureOrdered(ordering))))).let(::ResultGames)

    private fun getGames(): ResultGames =
        JSONObject(makeNetworkCall(URL(ApiService.queryFeature()))).let(::ResultGames)

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
