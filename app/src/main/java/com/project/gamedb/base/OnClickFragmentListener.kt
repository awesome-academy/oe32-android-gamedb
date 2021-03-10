package com.project.gamedb.base

import com.project.gamedb.data.model.GameSaved

interface OnClickFragmentListener {
    interface Details {
        fun openGameDetail(id: Int, genres: String)
    }

    interface More {
        fun openMoreFragment(id: String)
    }

    interface Save {
        fun addGame(game: GameSaved)
    }
}
