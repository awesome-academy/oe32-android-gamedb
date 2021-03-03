package com.project.gamedb.ui.ranking

import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteRepository
import com.project.gamedb.data.source.remote.OnDataLoadedCallback
import java.lang.Exception

class RankingPresenter(
    private val view: RankingContract.View,
    private val repository: GameRemoteRepository
) : RankingContract.Presenter {

    override fun getRankedGame(year: Int) {
        repository.getGameRanking(year, object : OnDataLoadedCallback<ResultGames> {
            override fun onSuccess(data: ResultGames) {
                view.showGame(data)
            }

            override fun onFailure(exception: Exception?) {
                view.showError(exception?.message.toString())
            }
        })
    }
}
