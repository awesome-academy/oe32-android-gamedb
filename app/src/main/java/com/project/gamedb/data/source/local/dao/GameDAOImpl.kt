package com.project.gamedb.data.source.local.dao

import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.data.source.local.GamesFavourite
import com.project.gamedb.data.source.local.database.AppDatabase

class GameDAOImpl private constructor(app: AppDatabase) : GameDAO {

    private val writableDatabase = app.writableDatabase
    private val readableDatabase = app.readableDatabase

    override fun getGames(): List<GameSaved> {
        val games = mutableListOf<GameSaved>()
        val cursor =
            readableDatabase.query(
                GamesFavourite.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
            )
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            games.add(GameSaved(cursor))
            cursor.moveToNext()
        }
        cursor.close()
        return games
    }

    override fun addGame(game: GameSaved): Boolean {
        val result = writableDatabase.insert(
            GamesFavourite.TABLE_NAME, null, game.getContentValue()
        )
        return result > 0
    }

    override fun removeGame(id: Int): Boolean {
        return writableDatabase.delete(
            GamesFavourite.TABLE_NAME,
            GameSaved.ID + "= ?",
            arrayOf(id.toString())
        ) > 0
    }

    companion object {
        private var instance: GameDAOImpl? = null

        fun getInstance(app: AppDatabase):
                GameDAOImpl =
            instance ?: synchronized(this) {
                instance ?: GameDAOImpl(app).also {
                    instance = it
                }
            }
    }
}
