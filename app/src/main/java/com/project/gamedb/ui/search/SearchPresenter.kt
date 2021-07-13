package com.project.gamedb.ui.search

import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteRepository
import com.project.gamedb.data.source.remote.OnDataLoadedCallback
import java.lang.Exception

class SearchPresenter(
    private val view: SearchContract.View,
    private val remote: GameRemoteRepository
):SearchContract.Presenter {

    override fun getSearchedGame(text: String) {
        remote.getGameSearched(text,object : OnDataLoadedCallback<ResultGames>{
            override fun onSuccess(data: ResultGames) {
                view.showGame(data)
            }

            override fun onFailure(exception: Exception?) {
                view.showError(exception?.message.toString())
            }
        })
    }
}
