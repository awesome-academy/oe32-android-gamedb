package com.project.gamedb.ui.saved

import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.data.source.local.GameLocalRepository
import com.project.gamedb.data.source.remote.OnDataLoadedCallback
import java.lang.Exception

class SavedGamePresenter(
    private val view: SavedGameContract.View,
    private val localData: GameLocalRepository
) : SavedGameContract.Presenter {

    override fun getGame() {
        localData.getGameSaved(object : OnDataLoadedCallback<List<GameSaved>> {
            override fun onSuccess(data: List<GameSaved>) {
                view.showGame(data)
            }

            override fun onFailure(exception: Exception?) {
                view.showResult(exception?.message.toString())
            }
        })
    }

    override fun addGame(game: GameSaved, info: String) {
        localData.addGame(game, object : OnDataLoadedCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                if (data) view.showResult(info)
            }

            override fun onFailure(exception: Exception?) {
                view.showResult(exception?.message.toString())
            }
        })
    }

    override fun removeGame(id: Int, info: String) {
        localData.removeGame(id, object : OnDataLoadedCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                if (data) view.showResult(info)
            }

            override fun onFailure(exception: Exception?) {
                view.showResult(exception?.message.toString())
            }
        })
    }
}
