package com.samkt.shared.domain.model

data class Game(
    val description: String,
    val developer: String,
    val freetogameProfileUrl: String,
    val gameUrl: String,
    val genre: String,
    val id: Int,
    val minimumSystemRequirements: MinimumSystemRequirements?,
    val platform: String,
    val publisher: String,
    val releaseDate: String,
    val screenshots: List<ScreenShot>,
    val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String
)
