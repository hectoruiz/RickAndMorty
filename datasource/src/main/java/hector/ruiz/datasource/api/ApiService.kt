package hector.ruiz.datasource.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    fun getStaticList(): Response<Nothing>

    @GET("character/}")
    fun getPaginatedList(@Query("page") pageNumber: Int): Response<Nothing>

    @GET("location/{location_id}")
    fun getLocation(@Path("location_id") id: Int): Response<Nothing>
}
