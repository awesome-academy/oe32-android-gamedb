package com.project.gamedb.ultis

import android.content.Context
import com.project.gamedb.data.source.local.GameLocalDataSource
import com.project.gamedb.data.source.local.GameLocalRepository
import com.project.gamedb.data.source.local.dao.GameDAOImpl
import com.project.gamedb.data.source.local.database.AppDatabase
import com.project.gamedb.data.source.remote.GameRemoteRepository
import com.project.gamedb.data.source.remote.GameRemoteDataSource

object Repositories {
    fun getRemoteRepository(gameRemoteDataSource: GameRemoteDataSource): GameRemoteRepository =
        GameRemoteRepository.getInstance(gameRemoteDataSource)

    fun getLocalRepository(context: Context): GameLocalRepository {
        val gameDao = GameDAOImpl.getInstance(AppDatabase.getInstance(context))
        return GameLocalRepository.getInstance(GameLocalDataSource.getInstance(gameDao))
    }
}
