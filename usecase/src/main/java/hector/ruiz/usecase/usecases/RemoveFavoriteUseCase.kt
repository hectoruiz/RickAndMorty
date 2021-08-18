package hector.ruiz.usecase.usecases

import hector.ruiz.usecase.repositories.FavoriteRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    fun removeFavorite(idCharacter: Int): Boolean {
        return favoriteRepository.removeFavorite(idCharacter)
    }
}
