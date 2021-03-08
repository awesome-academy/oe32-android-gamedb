package com.project.gamedb.data.source.remote

import com.project.gamedb.data.model.*
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
        }.execute()
    }

    override fun getGamesOrdered(ordering: String, callback: OnDataLoadedCallback<ResultGames>) {
        DataAsyncTask(callback) {
            getGamesOrdered(ordering)
        }.execute()
    }

    override fun getGameDetail(id: Long, callback: OnDataLoadedCallback<GameDetail>) {
        DataAsyncTask(callback) {
            getGameDetail(id)
        }.execute()
    }

    override fun getGenres(callback: OnDataLoadedCallback<ResultGenres>) {
        DataAsyncTask(callback) {
            getGenres()
        }.execute()
    }

    override fun getGameRanking(year: Int, callback: OnDataLoadedCallback<ResultGames>) {
        DataAsyncTask(callback) {
            getGameRanking(year)
        }.execute()
    }

    override fun getMoreOption(
        ordering: String,
        query: String,
        callback: OnDataLoadedCallback<ResultGames>
    ) {
        DataAsyncTask(callback) {
            getMoreOption(ordering, query)
        }.execute()
    }

    override fun getGenresInfo(info: String, callback: OnDataLoadedCallback<GenresDetails>) {
        DataAsyncTask(callback){
            getGenresInfo(info)
        }.execute()
    }

    private fun getGenresInfo(id: String): GenresDetails  =
        JSONObject(makeNetworkCall((URL(ApiService.queryGenresInfo(id))))).let(::GenresDetails)

    private fun getMoreOption(ordering: String, query: String): ResultGames =
        JSONObject(makeNetworkCall((URL(ApiService.queryMore(ordering, query))))).let(::ResultGames)

    private fun getGameDetail(id: Long): GameDetail =
        JSONObject(makeNetworkCall((URL(ApiService.queryGameDetail(id))))).let(::GameDetail)

    private fun getGamesOrdered(ordering: String): ResultGames =
        JSONObject(makeNetworkCall((URL(ApiService.queryFeatureOrdered(ordering))))).let(::ResultGames)

    private fun getGames(): ResultGames =
        JSONObject(makeNetworkCall(URL(ApiService.queryFeature()))).let(::ResultGames)

    private fun getGenres(): ResultGenres =
        JSONObject(makeNetworkCall(URL(ApiService.queryGenres()))).let(::ResultGenres)

    private fun getGameRanking(year: Int): ResultGames =
        JSONObject(makeNetworkCall(URL(ApiService.queryRanking(year)))).let(::ResultGames)

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
