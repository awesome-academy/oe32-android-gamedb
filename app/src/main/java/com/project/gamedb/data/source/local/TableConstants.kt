package com.project.gamedb.data.source.local

object GamesFavourite{
    const val TABLE_NAME = "favourite"

    const val ID = "id"
    const val NAME = "name"
    const val GENRES = "genres"
    const val IMAGE_URL = "image_url"
    const val RELEASED_DATE = "released_date"
    const val PLATFORM = "platform"
    const val META_SCORE = "meta_score"
    const val REVIEW_COUNT = "review_count"

    const val SQL_CREATE_TABLE =
        """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
        $ID INT ,
        $NAME TEXT PRIMARY KEY,
        $GENRES TEXT,
        $IMAGE_URL TEXT ,
        $RELEASED_DATE TEXT,
        $PLATFORM TEXT,
        $META_SCORE TINYINT,
        $REVIEW_COUNT TEXT
        ); """

    const val SQL_DROP_TABLE = """DROP TABLE IF EXISTS $TABLE_NAME"""
}
