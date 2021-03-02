package com.project.gamedb.ui.details

import com.project.gamedb.data.model.GameDetail

interface DetailsContract {
    interface View {
        fun showGameDetail(game: GameDetail)
        fun showError(error: String)
    }

    interface Presenter {
        fun getGameDetail(id: Long)
    }
}
