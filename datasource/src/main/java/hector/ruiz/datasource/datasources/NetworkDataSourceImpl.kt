package hector.ruiz.datasource.datasources

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.datasource.api.ApiService
import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters
import hector.ruiz.domain.entities.list.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(retrofit: Retrofit) : NetworkDataSource {

    private val service = retrofit.create<ApiService>()

    override suspend fun getListCharacters(): ResponseResult<Characters> {
        return withContext(Dispatchers.IO) {
            service.getStaticList().let {
                if (it.isSuccessful) {
                    ResponseResult(null, it.body())
                } else {
                    ResponseResult(it.code(), null)
                }
            }
        }
    }

    override suspend fun getCharacter(characterId: Int): ResponseResult<Results> {
        return withContext(Dispatchers.IO) {
            service.getCharacter(characterId).let {
                if (it.isSuccessful) {
                    ResponseResult(null, it.body())
                } else {
                    ResponseResult(it.code(), null)
                }
            }
        }
    }

    override suspend fun getPaginatedListCharacters(pageNumber: Int): ResponseResult<Characters> {
        return withContext(Dispatchers.IO) {
            service.getPaginatedList(pageNumber).let {
                if (it.isSuccessful) {
                    ResponseResult(null, it.body())
                } else {
                    ResponseResult(it.code(), null)
                }
            }
        }
    }

    override suspend fun getLocation(locationId: Int): ResponseResult<LocationDetails> {
        return withContext(Dispatchers.IO) {
            service.getLocation(locationId).let {
                if (it.isSuccessful) {
                    ResponseResult(null, it.body())
                } else {
                    ResponseResult(it.code(), null)
                }
            }
        }
    }
}
