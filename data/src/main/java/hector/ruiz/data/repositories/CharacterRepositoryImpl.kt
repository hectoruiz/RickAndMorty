package hector.ruiz.data.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters
import hector.ruiz.usecase.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource) :
    CharacterRepository {

    override fun getListCharacters(): ResponseResult<Characters> {
        return networkDataSource.getListCharacters()
    }

    override fun getPaginatedListCharacters(pageNumber: Int): ResponseResult<Characters> {
        return networkDataSource.getPaginatedListCharacters(pageNumber)
    }

    override fun getLocation(locationId: Int): ResponseResult<LocationDetails> {
        return networkDataSource.getLocation(locationId)
    }
}
