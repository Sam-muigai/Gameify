package com.samkt.gameify.di

import com.samkt.gameify.data.repository.GamesRepositoryImpl
import com.samkt.gameify.domain.repository.GamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGameRepository(
        gamesRepositoryImpl: GamesRepositoryImpl,
    ): GamesRepository
}

