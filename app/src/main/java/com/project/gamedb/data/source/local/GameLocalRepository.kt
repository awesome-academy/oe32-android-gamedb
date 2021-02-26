package com.project.gamedb.data.source.local

import com.project.gamedb.data.source.GameDataSource

class GameLocalRepository private constructor(private val local: GameDataSource.Local) :
    GameDataSource.Local {

    companion object {
        private var instance: GameLocalRepository? = null

        fun getInstance(
            local: GameDataSource.Local
        ) = instance ?: GameLocalRepository(local).also { instance = it }
    }

}
