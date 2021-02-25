package com.project.gamedb.data.source.remote

import com.project.gamedb.data.source.GameDataSource

class RemoteDataRepository private constructor(
    private val remote: GameDataSource.Remote
) : GameDataSource.Remote {

    companion object {
        private var instance: RemoteDataRepository? = null

        fun getInstance(
            remote: GameDataSource.Remote
        ) = instance ?: RemoteDataRepository(remote).also { instance = it }
    }
}
