package com.project.gamedb.ui.popular

import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteRepository
import com.project.gamedb.data.source.remote.OnDataLoadedCallback
import java.lang.Exception

class PopularPresenter(
    private val view: PopularContract.View,
    private val repositoryGame: GameRemoteRepository
) : PopularContract.Presenter {

    override fun getMainGameFeature() {
        repositoryGame.getGames(object : OnDataLoadedCallback<ResultGames> {
            override fun onSuccess(data: ResultGames) {
                view.showGame(data)
            }

            override fun onFailure(exception: Exception?) {
                view.showError(exception?.message.toString())
            }
        })
    }

    override fun getItemGameFeature(order: String) {
        repositoryGame.getGamesOrdered(order,object : OnDataLoadedCallback<ResultGames> {
            override fun onSuccess(data: ResultGames) {
                view.showItemGame(data)
            }

            override fun onFailure(exception: Exception?) {
                view.showError(exception?.message.toString())
            }
        })
    }
}
