package com.project.gamedb.ui.popular

import com.project.gamedb.base.BasePresenter
import com.project.gamedb.base.BaseView
import com.project.gamedb.data.model.ResultGames

interface PopularContract {
    interface View : BaseView {
        fun showGame(games: ResultGames)
        fun showItemGame(games: ResultGames)
        fun showError(error: String)
    }

    interface Presenter : BasePresenter {
        fun getMainGameFeature()
        fun getItemGameFeature()
    }
}
