package com.project.gamedb.ui.details

import com.project.gamedb.data.model.GameDetail
import com.project.gamedb.data.source.remote.GameRemoteRepository
import com.project.gamedb.data.source.remote.OnDataLoadedCallback
import java.lang.Exception

class DetailsPresenter(
    private val view: DetailsContract.View,
    private val repository: GameRemoteRepository
) : DetailsContract.Presenter {
    override fun getGameDetail(id: Long) {
        repository.getGameDetail(id, object : OnDataLoadedCallback<GameDetail> {
            override fun onSuccess(data: GameDetail) {
                view.showGameDetail(data)
            }

            override fun onFailure(exception: Exception?) {
                view.showError(exception?.message.toString())
            }
        })
    }
}
