package com.project.gamedb.base

interface OnLongClickHandler {
    var chooseState: Boolean

    fun handleDeleteButton()
    fun openGameDetail(id: Int, genres: String)
}
