package hector.ruiz.datasource.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiClient {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}
