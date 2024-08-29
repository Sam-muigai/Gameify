package com.samkt.shared.data.remote.mappers

import com.samkt.shared.data.remote.dto.GameDto
import com.samkt.shared.data.remote.dto.GamesResponseDto
import com.samkt.shared.data.remote.dto.MinimumSystemRequirementsDto
import com.samkt.shared.data.remote.dto.ScreenshotDto
import com.samkt.shared.domain.model.Game
import com.samkt.shared.domain.model.Games
import com.samkt.shared.domain.model.MinimumSystemRequirements
import com.samkt.shared.domain.model.ScreenShot

fun GamesResponseDto.toGames(): Games {
    return Games(
        developer,
        freeToGameProfileUrl,
        gameUrl,
        genre,
        id,
        platform,
        publisher,
        releaseDate,
        shortDescription,
        thumbnail,
        title,
    )
}

fun ScreenshotDto.toScreenShots(): ScreenShot {
    return ScreenShot(id, image)
}

fun MinimumSystemRequirementsDto.toMinimumRequirements(): MinimumSystemRequirements {
    return MinimumSystemRequirements(graphics, memory, os, processor, storage)
}

fun GameDto.toGame(): Game {
    return Game(
        description = description,
        developer = developer,
        freetogameProfileUrl = freetogameProfileUrl,
        gameUrl = gameUrl,
        genre = genre,
        id = id,
        minimumSystemRequirements = minimumSystemRequirements?.toMinimumRequirements(),
        platform = platform,
        publisher = publisher,
        releaseDate = releaseDate,
        screenshots = screenshots.map { it.toScreenShots() },
        shortDescription = shortDescription,
        status = status,
        thumbnail = thumbnail,
        title = title,
    )
}
