package com.samkt.shared.data.remote.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GamesResponseDto(
    @SerialName("developer")
    val developer: String,
    @SerialName("freetogame_profile_url")
    val freeToGameProfileUrl: String,
    @SerialName("game_url")
    val gameUrl: String,
    @SerialName("genre")
    val genre: String,
    @SerialName("id")
    val id: Int,
    @SerialName("platform")
    val platform: String,
    @SerialName("publisher")
    val publisher: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("short_description")
    val shortDescription: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String,
)
