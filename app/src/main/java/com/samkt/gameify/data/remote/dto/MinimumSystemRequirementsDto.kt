package com.samkt.gameify.data.remote.dto

import com.squareup.moshi.Json

data class MinimumSystemRequirementsDto(
    @field:Json(name = "graphics")
    val graphics: String?,
    @field:Json(name = "memory")
    val memory: String?,
    @field:Json(name = "os")
    val os: String?,
    @field:Json(name = "processor")
    val processor: String?,
    @field:Json(name = "storage")
    val storage: String?,
)
