package hector.ruiz.data.repositories

import hector.ruiz.data.datasources.MemoryDataSource
import hector.ruiz.usecase.repositories.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val memoryDataSource: MemoryDataSource) :
    FavoriteRepository {

    override fun addFavorite(idCharacter: Int): Boolean {
        return memoryDataSource.addFavorite(idCharacter)
    }

    override fun getFavorite(idCharacter: Int): Boolean {
        return memoryDataSource.getFavorite(idCharacter)
    }

    override fun removeFavorite(idCharacter: Int): Boolean {
        return memoryDataSource.removeFavorite(idCharacter)
    }
}
