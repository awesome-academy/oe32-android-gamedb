package com.project.gamedb.data.source.remote

import com.project.gamedb.data.model.*
import com.project.gamedb.data.source.GameDataSource

class GameRemoteRepository private constructor(
    private val remote: GameDataSource.Remote
) : GameDataSource.Remote {

    override fun getGames(callback: OnDataLoadedCallback<ResultGames>) {
        remote.getGames(callback)
    }

    override fun getGamesOrdered(ordering: String, callback: OnDataLoadedCallback<ResultGames>) {
        remote.getGamesOrdered(ordering, callback)
    }

    override fun getGameDetail(id: Long, callback: OnDataLoadedCallback<GameDetail>) {
        remote.getGameDetail(id, callback)
    }

    override fun getGenres(callback: OnDataLoadedCallback<ResultGenres>) {
        remote.getGenres(callback)
    }

    override fun getGameRanking(year: Int, callback: OnDataLoadedCallback<ResultGames>) {
        remote.getGameRanking(year, callback)
    }

    override fun getMoreOption(
        ordering: String,
        query: String,
        callback: OnDataLoadedCallback<ResultGames>
    ) {
        remote.getMoreOption(ordering, query, callback)
    }

    override fun getGenresInfo(info: String, callback: OnDataLoadedCallback<GenresDetails>) {
        remote.getGenresInfo(info, callback)
    }

    companion object {
        private var instance: GameRemoteRepository? = null

        fun getInstance(
            remote: GameDataSource.Remote
        ) = instance ?: GameRemoteRepository(remote).also { instance = it }
    }
}
