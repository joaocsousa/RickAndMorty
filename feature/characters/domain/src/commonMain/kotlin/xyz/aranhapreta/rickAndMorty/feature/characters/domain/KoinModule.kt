package xyz.aranhapreta.rickAndMorty.feature.characters.domain

import org.koin.dsl.module
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.usecases.ObserveCharacters
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.usecases.ObserveCharactersImpl

val featureCharactersDomainKoinModule = module {
    factory<ObserveCharacters> {
        ObserveCharactersImpl(charactersRepository = get())
    }
}