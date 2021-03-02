package com.project.gamedb.ui.genres

import com.project.gamedb.data.model.ResultGenres
import com.project.gamedb.data.source.remote.GameRemoteRepository
import com.project.gamedb.data.source.remote.OnDataLoadedCallback
import java.lang.Exception

class GenresPresenter(
    private val view: GenresContract.View,
    private val remote: GameRemoteRepository
): GenresContract.Presenter {

    override fun getGenres() {
        remote.getGenres(object : OnDataLoadedCallback<ResultGenres>{
            override fun onSuccess(data: ResultGenres) {
                view.showGenres(data)
            }

            override fun onFailure(exception: Exception?) {
                view.showError(exception?.message.toString())
            }
        })
    }
}
