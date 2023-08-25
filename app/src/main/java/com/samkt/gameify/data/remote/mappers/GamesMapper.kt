package com.samkt.gameify.data.remote.mappers

import com.samkt.gameify.data.remote.dto.GameDto
import com.samkt.gameify.data.remote.dto.GamesDtoItem
import com.samkt.gameify.data.remote.dto.MinimumSystemRequirementsDto
import com.samkt.gameify.data.remote.dto.ScreenshotDto
import com.samkt.gameify.domain.model.Game
import com.samkt.gameify.domain.model.Games
import com.samkt.gameify.domain.model.MinimumSystemRequirements
import com.samkt.gameify.domain.model.ScreenShot

fun GamesDtoItem.toGames(): Games {
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
