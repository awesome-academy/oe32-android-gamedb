package com.project.gamedb.ui.more

import com.project.gamedb.data.model.Games
import com.project.gamedb.data.model.GenresDetails
import com.project.gamedb.data.model.Platform
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteRepository
import com.project.gamedb.data.source.remote.OnDataLoadedCallback
import java.lang.Exception

class MorePresenter(
    private val view: MoreContract.View,
    private val repository: GameRemoteRepository
) : MoreContract.Presenter {
    override fun getGamesOrdered(ordering: String, query: String) {
        repository.getMoreOption(ordering, query, object : OnDataLoadedCallback<ResultGames>{
            override fun onSuccess(data: ResultGames) {
                view.showGames(data)
            }

            override fun onFailure(exception: Exception?) {
                view.showError(exception?.message.toString())
            }

        })
    }

    override fun getInfo(id: String) {
        repository.getGenresInfo(id, object : OnDataLoadedCallback<GenresDetails>{
            override fun onSuccess(data: GenresDetails) {
                view.showInfo(data.description)
            }

            override fun onFailure(exception: Exception?) {
                view.showError(exception?.message.toString())
            }
        })
    }
}
