package hector.ruiz.datasource.api

import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    fun getStaticList(): Response<Characters>

    @GET("character/}")
    fun getPaginatedList(@Query("page") pageNumber: Int): Response<Characters>

    @GET("location/{location_id}")
    fun getLocation(@Path("location_id") id: Int): Response<LocationDetails>
}
