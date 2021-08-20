package hector.ruiz.usecase.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.usecase.repositories.CharacterRepository
import javax.inject.Inject

class GetLocationDetailsUseCase @Inject constructor(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke(locationId: Int): ResponseResult<LocationDetails> {
        return characterRepository.getLocation(locationId)
    }
}
