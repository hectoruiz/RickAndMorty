package hector.ruiz.datasource.datasources

import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.datasource.api.ApiClient
import hector.ruiz.datasource.api.ApiService
import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters
import retrofit2.create
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(apiClient: ApiClient) : NetworkDataSource {

    private val service = apiClient.retrofit.create<ApiService>()

    override fun getListCharacters(): Characters? {
        return service.getStaticList().body()
    }

    override fun getPaginatedListCharacters(pageNumber: Int): Characters? {
        return service.getPaginatedList(pageNumber).body()
    }

    override fun getLocation(locationId: Int): LocationDetails? {
        return service.getLocation(locationId).body()
    }
}
