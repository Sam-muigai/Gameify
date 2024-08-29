package com.samkt.shared.data.remote.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MinimumSystemRequirementsDto(
    @SerialName("graphics")
    val graphics: String? = null,
    @SerialName("memory")
    val memory: String? = null,
    @SerialName("os")
    val os: String? = null,
    @SerialName("processor")
    val processor: String? = null,
    @SerialName("storage")
    val storage: String? = null,
)

