package hector.ruiz.usecase.repositories

interface FavoriteRepository {

    suspend fun addFavorite(idCharacter: Int): Boolean

    suspend fun getFavorite(idCharacter: Int): Boolean

    suspend fun removeFavorite(idCharacter: Int): Boolean
}
