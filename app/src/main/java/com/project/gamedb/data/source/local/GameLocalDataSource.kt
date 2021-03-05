package com.project.gamedb.data.source.local

import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.data.source.DataAsyncTask
import com.project.gamedb.data.source.GameDataSource
import com.project.gamedb.data.source.local.dao.GameDAO
import com.project.gamedb.data.source.remote.OnDataLoadedCallback

class GameLocalDataSource private constructor(private val gameDAO: GameDAO) : GameDataSource.Local {
    override fun getGameSaved(callback: OnDataLoadedCallback<List<GameSaved>>) {
        DataAsyncTask(callback) {
            getGame()
        }.execute("")
    }

    override fun addGame(game: GameSaved, callback: OnDataLoadedCallback<Boolean>) {
        gameDAO.addGame(game)
    }

    override fun removeGame(id: Int, callback: OnDataLoadedCallback<Boolean>) {
        gameDAO.removeGame(id)
    }

    private fun getGame(): List<GameSaved> = gameDAO.getGames()

    companion object {
        private var instance: GameLocalDataSource? = null

        fun getInstance(cityDao: GameDAO) =
            instance ?: GameLocalDataSource(cityDao).also { instance = it }
    }
}
