package hector.ruiz.datasource.api

import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters
import hector.ruiz.domain.entities.list.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getStaticList(): Response<Characters>

    @GET("character/{character_id}")
    suspend fun getCharacter(@Path("character_id") id: Int): Response<Results>

    @GET("character/}")
    suspend fun getPaginatedList(@Query("page") pageNumber: Int): Response<Characters>

    @GET("location/{location_id}")
    suspend fun getLocation(@Path("location_id") id: Int): Response<LocationDetails>
}
