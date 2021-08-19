package hector.ruiz.data.datasources

interface MemoryDataSource {

    suspend fun addFavorite(idCharacter: Int): Boolean

    suspend fun getFavorite(idCharacter: Int): Boolean

    suspend fun removeFavorite(idCharacter: Int): Boolean
}
