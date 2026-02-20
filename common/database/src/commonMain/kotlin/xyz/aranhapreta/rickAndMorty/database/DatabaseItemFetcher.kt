package xyz.aranhapreta.rickAndMorty.database

interface DatabaseItemFetcher<DATA> {
    suspend fun getItems(limit: Int, offset: Int): List<DATA>
}
