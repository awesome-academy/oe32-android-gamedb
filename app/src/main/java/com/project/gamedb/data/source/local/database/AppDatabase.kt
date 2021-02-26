package com.project.gamedb.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.project.gamedb.data.source.local.GamesFavourite

class AppDatabase private constructor(context: Context) :
    SQLiteOpenHelper(context, NAME_DB, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase) = with(db) {
        execSQL(GamesFavourite.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) = with(db) {
        execSQL(GamesFavourite.SQL_DROP_TABLE)
        onCreate(db)
    }

    companion object {
        private const val NAME_DB = "gamer.db"
        private const val VERSION = 1
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: AppDatabase(context).also {
                    instance = it
                }
            }
    }
}
