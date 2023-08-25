package com.samkt.gameify.data.remote.dto

import com.squareup.moshi.Json

data class ScreenshotDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "image")
    val image: String,
)
