package com.project.gamedb.ultis

object PlatformConstants {
    const val PC = "PC"
    const val XBOX = "Xbox"
    const val PLAYSTATION = "PlayStation"
    const val NINTENDO = "Nintendo"
    const val IOS = "iOS"
    const val ANDROID = "Android"

    const val PC_ID = "4"
    const val XBOX_ID = "1,186,14,80"
    const val PLAYSTATION_ID = "187,18,16,15,27"
    const val NINTENDO_ID = "7,8,9,13,83"
    const val MOBILE_ID = "3,21"

    val platforms = listOf(PC, ANDROID, IOS, NINTENDO, PLAYSTATION, XBOX)
}
