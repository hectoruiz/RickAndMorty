package hector.ruiz.data.datasources

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters

interface NetworkDataSource {

    fun getListCharacters(): ResponseResult<Characters>

    fun getPaginatedListCharacters(pageNumber: Int): ResponseResult<Characters>

    fun getLocation(locationId: Int): ResponseResult<LocationDetails>
}
