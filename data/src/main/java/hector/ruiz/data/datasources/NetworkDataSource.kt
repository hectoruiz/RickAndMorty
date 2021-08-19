package hector.ruiz.data.datasources

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters

interface NetworkDataSource {

    suspend fun getListCharacters(): ResponseResult<Characters>

    suspend fun getPaginatedListCharacters(pageNumber: Int): ResponseResult<Characters>

    suspend fun getLocation(locationId: Int): ResponseResult<LocationDetails>
}
