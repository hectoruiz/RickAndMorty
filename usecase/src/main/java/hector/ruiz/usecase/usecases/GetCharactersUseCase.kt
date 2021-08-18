package hector.ruiz.usecase.usecases

import hector.ruiz.domain.entities.list.Characters
import hector.ruiz.usecase.repositories.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {

    fun getCharacters(pageNumber: Int?): Characters? {
        return pageNumber?.let {
            characterRepository.getPaginatedListCharacters(pageNumber)
        } ?: characterRepository.getListCharacters()
    }
}
