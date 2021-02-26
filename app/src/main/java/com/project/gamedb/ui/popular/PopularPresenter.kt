package com.project.gamedb.ui.popular

import com.project.gamedb.data.source.remote.GameRemoteRepository

class PopularPresenter(
    private val view: PopularContract.View,
    private val repositoryGame: GameRemoteRepository
) : PopularContract.Presenter {

    override fun getMainGameFeature() {
    }

    override fun getItemGameFeature() {
    }

}
