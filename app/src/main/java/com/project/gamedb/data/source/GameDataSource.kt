package com.project.gamedb.data.source

import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.OnDataLoadedCallback

interface GameDataSource {
    interface Local {
    }

    interface Remote {
        fun getGames(callback: OnDataLoadedCallback<ResultGames>)
        fun getGamesOrdered(ordering: String, callback: OnDataLoadedCallback<ResultGames>)
    }
}
