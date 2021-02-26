package com.project.gamedb.ultis

import com.project.gamedb.data.source.remote.GameRemoteRepository
import com.project.gamedb.data.source.remote.GameRemoteDataSource

object Repositories {
    fun getRemoteRepository(gameRemoteDataSource: GameRemoteDataSource): GameRemoteRepository =
        GameRemoteRepository.getInstance(gameRemoteDataSource)
}
