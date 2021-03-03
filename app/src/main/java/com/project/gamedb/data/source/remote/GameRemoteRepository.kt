package com.project.gamedb.data.source.remote

import com.project.gamedb.data.model.GameDetail
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.model.ResultGenres
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

    companion object {
        private var instance: GameRemoteRepository? = null

        fun getInstance(
            remote: GameDataSource.Remote
        ) = instance ?: GameRemoteRepository(remote).also { instance = it }
    }
}
