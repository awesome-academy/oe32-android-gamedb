package com.project.gamedb.data.source.local.dao

import com.project.gamedb.data.model.GameSaved

interface GameDAO {
    fun getGames(): List<GameSaved>
    fun addGame(game: GameSaved): Boolean
    fun removeGame(id: Int): Boolean
}
