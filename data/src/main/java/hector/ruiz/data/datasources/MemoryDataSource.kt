package hector.ruiz.data.datasources

interface MemoryDataSource {

    fun addFavorite(idCharacter: Int): Boolean

    fun getFavorite(idCharacter: Int): Boolean

    fun removeFavorite(idCharacter: Int): Boolean
}
