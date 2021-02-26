package com.project.gamedb.data.source.remote

import com.project.gamedb.data.source.GameDataSource

class GameRemoteRepository private constructor(
    private val remote: GameDataSource.Remote
) : GameDataSource.Remote {

    companion object {
        private var instance: GameRemoteRepository? = null

        fun getInstance(
            remote: GameDataSource.Remote
        ) = instance ?: GameRemoteRepository(remote).also { instance = it }
    }
}
