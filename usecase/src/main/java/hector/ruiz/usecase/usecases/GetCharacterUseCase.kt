package hector.ruiz.usecase.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.list.Results
import hector.ruiz.usecase.repositories.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke(characterId: Int): ResponseResult<Results> {
        return characterRepository.getCharacter(characterId)
    }
}
