package com.samkt.shared.data.remote.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GameDto(
    @SerialName("description")
    val description: String,
    @SerialName("developer")
    val developer: String,
    @SerialName("freetogame_profile_url")
    val freetogameProfileUrl: String,
    @SerialName("game_url")
    val gameUrl: String,
    @SerialName("genre")
    val genre: String,
    @SerialName("id")
    val id: Int,
    @SerialName("minimum_system_requirements")
    val minimumSystemRequirements: MinimumSystemRequirementsDto? = null,
    @SerialName("platform")
    val platform: String,
    @SerialName("publisher")
    val publisher: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("screenshots")
    val screenshots: List<ScreenshotDto>,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("status")
    val status: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String,
)

