package hector.ruiz.usecase.usecases

import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.usecase.repositories.CharacterRepository
import javax.inject.Inject

class GetLocationDetailsUseCase @Inject constructor(private val characterRepository: CharacterRepository) {

    fun getLocationDetails(locationId: Int): LocationDetails? {
        return characterRepository.getLocation(locationId)
    }
}
