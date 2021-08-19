package hector.ruiz.datasource.datasources

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.datasource.api.ApiService
import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(retrofit: Retrofit) : NetworkDataSource {

    private val service = retrofit.create<ApiService>()

    override fun getListCharacters(): ResponseResult<Characters> {
        return service.getStaticList().let {
            if (it.isSuccessful) {
                ResponseResult(null, it.body())
            } else {
                ResponseResult(it.code(), null)
            }
        }
    }

    override fun getPaginatedListCharacters(pageNumber: Int): ResponseResult<Characters> {
        return service.getPaginatedList(pageNumber).let {
            if (it.isSuccessful) {
                ResponseResult(null, it.body())
            } else {
                ResponseResult(it.code(), null)
            }
        }
    }

    override fun getLocation(locationId: Int): ResponseResult<LocationDetails> {
        return service.getLocation(locationId).let {
            if (it.isSuccessful) {
                ResponseResult(null, it.body())
            } else {
                ResponseResult(it.code(), null)
            }
        }
    }
}
