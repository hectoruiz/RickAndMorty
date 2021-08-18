package hector.ruiz.usecase.repositories

import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters

interface CharacterRepository {

    fun getListCharacters(): Characters?

    fun getPaginatedListCharacters(pageNumber: Int): Characters?

    fun getLocation(locationId: Int): LocationDetails?
}
