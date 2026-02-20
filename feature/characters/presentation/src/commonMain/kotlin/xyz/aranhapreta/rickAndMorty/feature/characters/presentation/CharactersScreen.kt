package xyz.aranhapreta.rickAndMorty.feature.characters.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.AddressBook
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import rickandmorty.feature.characters.presentation.generated.resources.Res
import rickandmorty.feature.characters.presentation.generated.resources.gender_female
import rickandmorty.feature.characters.presentation.generated.resources.gender_genderless
import rickandmorty.feature.characters.presentation.generated.resources.gender_label
import rickandmorty.feature.characters.presentation.generated.resources.gender_male
import rickandmorty.feature.characters.presentation.generated.resources.gender_unknown
import rickandmorty.feature.characters.presentation.generated.resources.species_label
import rickandmorty.feature.characters.presentation.generated.resources.status_alive
import rickandmorty.feature.characters.presentation.generated.resources.status_dead
import rickandmorty.feature.characters.presentation.generated.resources.status_label
import rickandmorty.feature.characters.presentation.generated.resources.status_unknown
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterGender
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterScreenEvent
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterState
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.models.CharacterStatus

@Composable
fun CharactersScreen(contentPadding: PaddingValues) {
    Logger.i { "CharactersScreen recomposing" }
    val viewModel = koinViewModel<CharactersViewModel>()
    val characters = viewModel.characters.collectAsLazyPagingItems()

    CharactersList(
        characters = characters,
        onEvent = viewModel::onEvent,
        contentPadding = contentPadding,
    )
}

@Composable
private fun CharactersList(
    characters: LazyPagingItems<CharacterState>,
    onEvent: (CharacterScreenEvent) -> Unit,
    contentPadding: PaddingValues
) {
    Logger.i { "CharactersList recomposing. Item count: ${characters.itemCount}" }
    LaunchedEffect(characters.loadState) {
        Logger.d { "Paging LoadState changed: ${characters.loadState}" }
    }

    val padding = 24.dp
    val topPadding = contentPadding.calculateTopPadding() + padding
    val bottomPadding = contentPadding.calculateBottomPadding() + padding

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = spacedBy(24.dp),
        contentPadding = PaddingValues(
            start = padding,
            top = topPadding,
            end = padding,
            bottom = bottomPadding
        ),
    ) {
        items(
            count = characters.itemCount,
            key = characters.itemKey { it.id }
        ) { index ->
            val item = characters[index]
            if (item != null) {
                CharacterItem(
                    modifier = Modifier.fillMaxWidth(),
                    item = item,
                    onEvent = onEvent,
                )
            }
        }

        // Handle loading states
        when (characters.loadState.append) {
            is LoadState.Loading -> {
                item {
                    Box(
                        Modifier.fillMaxWidth().padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is LoadState.Error -> {
                item {
                    TextButton(
                        onClick = {},
                        shape = ButtonDefaults.shape
                    ) {
                        Text("Text Button")
                    }
                }
            }

            else -> {}
        }
    }

    // Handle initial load state
    when (val loadState = characters.loadState.refresh) {
        is LoadState.Loading -> {
            Box(Modifier.fillMaxSize().systemBarsPadding(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            Box(Modifier.fillMaxSize().systemBarsPadding(), contentAlignment = Alignment.Center) {
                Text("Error: ${loadState.error.message}")
            }
        }

        else -> {}
    }
}


@Composable
private fun CharacterItem(
    modifier: Modifier,
    item: CharacterState,
    onEvent: (CharacterScreenEvent) -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        onClick = { onEvent(CharacterScreenEvent.CharacterClicked(item.id)) }
    ) {
        Row(
            Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = item.imageUrl,
                modifier = Modifier.size(80.dp).clip(CircleShape),
                contentDescription = null,
                error = rememberVectorPainter(FontAwesomeIcons.Regular.AddressBook)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
            ) {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Text(text = statusLabel(item.status), style = MaterialTheme.typography.labelLarge)
                Text(text = genderLabel(item.gender), style = MaterialTheme.typography.labelLarge)
                Text(
                    text = speciesLabel(item.species, item.type),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
private fun statusLabel(status: CharacterStatus): String {
    return stringResource(Res.string.status_label, status.label())
}

@Composable
private fun genderLabel(gender: CharacterGender): String {
    return stringResource(Res.string.gender_label, gender.label())
}

@Composable
private fun speciesLabel(species: String, type: String): String {
    val speciesLabel = listOf(species, type).filter { it.isNotBlank() }.joinToString(", ")
    return stringResource(Res.string.species_label, speciesLabel)
}

@Composable
private fun CharacterGender.label(): String {
    val resource = when (this) {
        CharacterGender.Female -> Res.string.gender_female
        CharacterGender.Genderless -> Res.string.gender_genderless
        CharacterGender.Male -> Res.string.gender_male
        CharacterGender.Unknown -> Res.string.gender_unknown
    }
    return stringResource(resource)
}

@Composable
private fun CharacterStatus.label(): String {
    val resource = when (this) {
        CharacterStatus.Alive -> Res.string.status_alive
        CharacterStatus.Dead -> Res.string.status_dead
        CharacterStatus.Unknown -> Res.string.status_unknown
    }
    return stringResource(resource)
}

@Preview
@Composable
private fun asd() {
    Box(Modifier.fillMaxSize().systemBarsPadding(), contentAlignment = Alignment.Center) {
        Text("Error: This is a message")
    }
}