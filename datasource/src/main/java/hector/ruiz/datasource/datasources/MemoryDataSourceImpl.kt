package hector.ruiz.datasource.datasources

import hector.ruiz.data.datasources.MemoryDataSource
import hector.ruiz.datasource.sharedpreferences.ApiPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemoryDataSourceImpl @Inject constructor(apiPreferences: ApiPreferences) :
    MemoryDataSource {

    private val sharedPreferences = apiPreferences.sharedPreferences

    override suspend fun addFavorite(idCharacter: Int): Boolean {
        return withContext(Dispatchers.IO) {
            with(sharedPreferences.edit()) {
                putString(NAME + idCharacter, idCharacter.toString())
                commit()
            }
        }
    }

    override suspend fun getFavorite(idCharacter: Int): Boolean {
        return withContext(Dispatchers.IO) {
            sharedPreferences.contains(NAME + idCharacter)
        }
    }

    override suspend fun removeFavorite(idCharacter: Int): Boolean {
        return withContext(Dispatchers.IO) {
            with(sharedPreferences.edit()) {
                remove(NAME + idCharacter)
                commit()
            }
        }
    }

    companion object {
        const val NAME = "Character="
    }
}
