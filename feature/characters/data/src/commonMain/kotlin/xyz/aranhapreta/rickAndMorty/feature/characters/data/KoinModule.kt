package xyz.aranhapreta.rickAndMorty.feature.characters.data

import org.koin.dsl.module
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.repository.CharactersRepository

val featureCharactersDataKoinModule = module {
    factory<CharactersRepository> {
        CharactersRepositoryImpl(
            charactersDao = get(),
            charactersRemoteMediator = get(),
        )
    }
    factory {
        CharactersRemoteMediator(
            charactersApi = get(),
            charactersDao = get(),
            remoteKeysDao = get()
        )
    }
}