package com.project.gamedb.ui.ranking

import com.project.gamedb.base.BasePresenter
import com.project.gamedb.base.BaseView
import com.project.gamedb.data.model.ResultGames
import java.util.*

interface RankingContract {
    interface View : BaseView {
        fun showGame(list: ResultGames)
        fun showError(error: String)
    }

    interface Presenter : BasePresenter {
        fun getRankedGame(year: Int)
    }
}
