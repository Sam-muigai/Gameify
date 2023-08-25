package com.samkt.gameify.data.remote.dto

import com.squareup.moshi.Json

data class GamesDtoItem(
    val developer: String,
    @field:Json(name = "freetogame_profile_url") val freeToGameProfileUrl: String,
    @field:Json(name = "game_url") val gameUrl: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val publisher: String,
    @field:Json(name = "release_date") val releaseDate: String,
    @field:Json(name = "short_description") val shortDescription: String,
    val thumbnail: String,
    val title: String,
)
