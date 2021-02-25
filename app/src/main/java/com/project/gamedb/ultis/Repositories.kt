package com.project.gamedb.ultis

import com.project.gamedb.data.source.remote.RemoteDataRepository
import com.project.gamedb.data.source.remote.RemoteDataSource

object Repositories {
    fun getRemoteRepository(remoteDataSource: RemoteDataSource): RemoteDataRepository =
        RemoteDataRepository.getInstance(remoteDataSource)
}
