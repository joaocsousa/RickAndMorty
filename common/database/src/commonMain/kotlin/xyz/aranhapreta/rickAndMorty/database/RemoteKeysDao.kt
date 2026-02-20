package xyz.aranhapreta.rickAndMorty.database

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import xyz.aranhapreta.rickAndMorty.database.RemoteKeysDao.QueryType

/**
 * Data Access Object for managing RemoteKey entities.
 *
 * This class provides a clean API for interacting with the `remoteKeyDbModel` table,
 * abstracting the direct SQLDelight query calls. It is designed to be a singleton
 * or provided via dependency injection.
 */
interface RemoteKeysDao {
    suspend fun getRemoteKeyByType(type: QueryType): RemoteKey?
    suspend fun insertKey(type: QueryType, nextKey: Long?)
    suspend fun clearAllRemoteKeys()

    enum class QueryType(val value: String) {
        Characters("characters"),
        Locations("locations"),
        Episodes("episodes"),
    }
}

internal class RemoteKeysDaoImpl(
    database: Database,
    private val ioDispatcher: CoroutineDispatcher,
) : RemoteKeysDao {

    private val remoteKeyQueries = database.remote_keysQueries

    /**
     * Retrieves the remote key for a specific paginated list.
     *
     * @param type The type for the list (e.g., "characters", "locations").
     * @return The [RemoteKey] if it exists, otherwise null.
     */
    override suspend fun getRemoteKeyByType(type: QueryType): RemoteKey? {
        return withContext(ioDispatcher) {
            Logger.d { "Getting remote key for type: ${type.value}" }
            val key = remoteKeyQueries
                .getRemoteKeyByType(type = type.value)
                .executeAsOneOrNull()
            Logger.d { "Remote key for ${type.value} is: ${key?.nextKey}" }
            key
        }
    }

    /**
     * Inserts or replaces a remote key for a specific paginated list.
     *
     * @param type The unique type for the list.
     * @param nextKey The key for the next page to be fetched, or null if the end of the list has been reached.
     */
    override suspend fun insertKey(type: QueryType, nextKey: Long?) {
        withContext(ioDispatcher) {
            Logger.d { "Inserting remote key for type: ${type.value}, nextKey: $nextKey" }
            remoteKeyQueries.insertKey(type = type.value, nextKey = nextKey)
                .await()
        }
    }

    /**
     * Deletes all remote keys from the table.
     * This is typically used during a full database refresh or user logout.
     */
    override suspend fun clearAllRemoteKeys() {
        withContext(ioDispatcher) {
            val count = remoteKeyQueries.clearAll().value
            Logger.i { "Cleared $count remote keys" }
        }
    }
}
