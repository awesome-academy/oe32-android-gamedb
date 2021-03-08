package com.project.gamedb.ui.more

import com.project.gamedb.base.BasePresenter
import com.project.gamedb.base.BaseView
import com.project.gamedb.data.model.ResultGames

interface MoreContract {
    interface View: BaseView {
        fun showGames(result: ResultGames)
        fun showError(error: String)
        fun showInfo(info: String)
    }
    interface Presenter: BasePresenter {
        fun getGamesOrdered(ordering: String, query: String)
        fun getInfo(id: String)
    }
}
