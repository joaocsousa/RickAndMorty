package xyz.aranhapreta.rickAndMorty.di

import xyz.aranhapreta.feature.episodes.presentation.featureEpisodesPresentationKoinModule
import xyz.aranhapreta.feature.locations.presentation.featureLocationsPresentationKoinModule
import xyz.aranhapreta.rickAndMorty.api.apiCoreModule
import xyz.aranhapreta.rickAndMorty.database.databaseKoinModule
import xyz.aranhapreta.rickAndMorty.feature.characters.data.featureCharactersDataKoinModule
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.featureCharactersDomainKoinModule
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.featureCharactersPresentationKoinModule

val koinModules = listOf(
    apiCoreModule,
    databaseKoinModule,
    featureCharactersDataKoinModule,
    featureCharactersDomainKoinModule,
    featureCharactersPresentationKoinModule,
    featureEpisodesPresentationKoinModule,
    featureLocationsPresentationKoinModule,
)
