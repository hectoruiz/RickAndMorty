package hector.ruiz.usecase.usecases

import hector.ruiz.usecase.repositories.FavoriteRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    fun addFavorite(idCharacter: Int): Boolean {
        return favoriteRepository.addFavorite(idCharacter)
    }
}
