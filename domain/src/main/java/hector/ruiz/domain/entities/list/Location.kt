package hector.ruiz.domain.entities.list

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location (
    val name : String?,
    val url : String?
)
