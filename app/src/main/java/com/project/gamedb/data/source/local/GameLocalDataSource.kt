package com.project.gamedb.data.source.local

import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.data.source.GameDataSource
import com.project.gamedb.data.source.local.dao.GameDAO

class GameLocalDataSource private constructor(private val gameDAO: GameDAO) : GameDataSource.Local {

    private fun getGame(): List<GameSaved> = gameDAO.getGames()

    companion object {
        private var instance: GameLocalDataSource? = null

        fun getInstance(cityDao: GameDAO) =
            instance ?: GameLocalDataSource(cityDao).also { instance = it }
    }
}
