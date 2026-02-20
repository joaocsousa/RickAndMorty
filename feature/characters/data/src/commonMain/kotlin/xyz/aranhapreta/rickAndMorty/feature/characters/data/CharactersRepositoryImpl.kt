package xyz.aranhapreta.rickAndMorty.feature.characters.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import xyz.aranhapreta.rickAndMorty.database.Character
import xyz.aranhapreta.rickAndMorty.database.CharactersDao
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.entities.Gender
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.entities.Status
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.repository.CharactersRepository
import xyz.aranhapreta.rickAndMorty.feature.characters.domain.entities.Character as CharacterDomain

@OptIn(ExperimentalPagingApi::class)
internal class CharactersRepositoryImpl(
    private val charactersDao: CharactersDao,
    private val charactersRemoteMediator: CharactersRemoteMediator,
) : CharactersRepository {


    override fun getCharacters(): Flow<PagingData<CharacterDomain>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = charactersRemoteMediator,
            pagingSourceFactory = { charactersDao.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomainModel() }
        }
    }
}

private fun Character.toDomainModel() = CharacterDomain(
    id = id.toString(),
    name = name,
    image = image,
    status = when (status) {
        "Alive" -> Status.Alive
        "Dead" -> Status.Dead
        else -> Status.Unknown
    },
    species = species,
    type = type,
    gender = when (gender) {
        "Female" -> Gender.Female
        "Male" -> Gender.Male
        "Genderless" -> Gender.Genderless
        else -> Gender.Unknown
    },
)
