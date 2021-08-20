package hector.ruiz.usecase.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters
import hector.ruiz.domain.entities.list.Results

interface CharacterRepository {

    suspend fun getListCharacters(): ResponseResult<Characters>

    suspend fun getCharacter(characterId: Int): ResponseResult<Results>

    suspend fun getPaginatedListCharacters(pageNumber: Int): ResponseResult<Characters>

    suspend fun getLocation(locationId: Int): ResponseResult<LocationDetails>
}
