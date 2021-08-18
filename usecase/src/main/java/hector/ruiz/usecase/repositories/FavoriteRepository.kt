package hector.ruiz.usecase.repositories

interface FavoriteRepository {

    fun addFavorite(idCharacter: Int): Boolean

    fun getFavorite(idCharacter: Int): Boolean

    fun removeFavorite(idCharacter: Int): Boolean
}
