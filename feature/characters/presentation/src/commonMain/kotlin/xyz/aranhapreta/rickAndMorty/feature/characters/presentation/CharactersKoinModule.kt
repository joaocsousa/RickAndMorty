package xyz.aranhapreta.rickAndMorty.feature.characters.presentation

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureCharactersPresentationKoinModule = module {
    viewModel {
        CharactersViewModel(observeCharacters = get())
    }
}