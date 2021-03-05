package com.project.gamedb.ui.saved

import com.project.gamedb.base.BasePresenter
import com.project.gamedb.base.BaseView
import com.project.gamedb.data.model.GameSaved

interface SavedGameContract {
    interface View : BaseView {
        fun showGame(list: List<GameSaved>)
        fun showResult(result: String)
    }

    interface Presenter : BasePresenter {
        fun getGame()
        fun addGame(game: GameSaved, info: String)
        fun removeGame(id: Int, info: String)
    }
}
