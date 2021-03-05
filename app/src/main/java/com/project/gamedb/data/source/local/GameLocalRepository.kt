package com.project.gamedb.data.source.local

import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.data.source.GameDataSource
import com.project.gamedb.data.source.remote.OnDataLoadedCallback

class GameLocalRepository private constructor(private val local: GameDataSource.Local) :
    GameDataSource.Local {

    override fun getGameSaved(callback: OnDataLoadedCallback<List<GameSaved>>) {
        local.getGameSaved(callback)
    }

    override fun addGame(game: GameSaved, callback: OnDataLoadedCallback<Boolean>) {
        local.addGame(game, callback)
    }

    override fun removeGame(id: Int, callback: OnDataLoadedCallback<Boolean>) {
        local.removeGame(id, callback)
    }

    companion object {
        private var instance: GameLocalRepository? = null

        fun getInstance(
            local: GameDataSource.Local
        ) = instance ?: GameLocalRepository(local).also { instance = it }
    }
}
