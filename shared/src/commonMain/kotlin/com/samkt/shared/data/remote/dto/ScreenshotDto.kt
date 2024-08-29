package com.samkt.shared.data.remote.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ScreenshotDto(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
)