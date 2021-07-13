package com.project.gamedb.ui.search

import com.project.gamedb.base.BasePresenter
import com.project.gamedb.base.BaseView
import com.project.gamedb.data.model.ResultGames

interface SearchContract {
    interface View : BaseView {
        fun showGame(games: ResultGames)
        fun showError(error: String)
    }

    interface Presenter : BasePresenter {
        fun getSearchedGame(text: String)
    }
}
