package hector.ruiz.datasource.datasources

import android.content.SharedPreferences
import hector.ruiz.data.datasources.MemoryDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemoryDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    MemoryDataSource {

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
