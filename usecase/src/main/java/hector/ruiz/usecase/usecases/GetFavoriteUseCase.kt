package hector.ruiz.usecase.usecases

import hector.ruiz.usecase.repositories.FavoriteRepository
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    fun getFavorite(idCharacter: Int): Boolean {
        return favoriteRepository.getFavorite(idCharacter)
    }
}
