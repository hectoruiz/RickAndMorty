package hector.ruiz.domain.entities.details

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationDetails (
    val id : Int?,
    val name : String?,
    val type : String?,
    val dimension : String?,
    val residents : List<String?>?,
    val url : String?,
    val created : String?
)