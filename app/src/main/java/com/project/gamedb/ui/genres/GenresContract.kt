package com.project.gamedb.ui.genres

import com.project.gamedb.base.BasePresenter
import com.project.gamedb.base.BaseView
import com.project.gamedb.data.model.Genres
import com.project.gamedb.data.model.ResultGenres

interface GenresContract {
    interface View: BaseView {
        fun showGenres(list: ResultGenres)
        fun showError(error: String)
    }
    interface Presenter: BasePresenter {
        fun getGenres()
    }
}
