package com.project.gamedb.data.source.local

import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.data.source.GameDataSource
import com.project.gamedb.data.source.local.dao.GameDAO

class LocalDataSource private constructor(private val gameDAO: GameDAO) : GameDataSource.Local {

    private fun getGame(): List<GameSaved> = gameDAO.getGames()

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(cityDao: GameDAO) =
            instance ?: LocalDataSource(cityDao).also { instance = it }
    }
}
