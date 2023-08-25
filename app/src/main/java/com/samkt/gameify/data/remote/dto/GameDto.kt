package com.samkt.gameify.data.remote.dto

import com.squareup.moshi.Json

data class GameDto(
    val description: String,
    val developer: String,
    @field:Json(name = "freetogame_profile_url") val freetogameProfileUrl: String,
    @field:Json(name = "game_url") val gameUrl: String,
    val genre: String,
    val id: Int,
    @field:Json(name = "minimum_system_requirements") val minimumSystemRequirements: MinimumSystemRequirementsDto?,
    val platform: String,
    val publisher: String,
    @field:Json(name = "release_date") val releaseDate: String,
    val screenshots: List<ScreenshotDto>,
    @field:Json(name = "short_description") val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String,
)
