package com.project.gamedb.data.source

import com.project.gamedb.data.model.GameDetail
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.model.ResultGenres
import com.project.gamedb.data.source.remote.OnDataLoadedCallback

interface GameDataSource {
    interface Local {}

    interface Remote {
        fun getGames(callback: OnDataLoadedCallback<ResultGames>)
        fun getGamesOrdered(ordering: String, callback: OnDataLoadedCallback<ResultGames>)
        fun getGameDetail(id: Long, callback: OnDataLoadedCallback<GameDetail>)
        fun getGenres(callback: OnDataLoadedCallback<ResultGenres>)
        fun getGameRanking(year: Int, callback: OnDataLoadedCallback<ResultGames>)
    }
}
