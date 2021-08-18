package hector.ruiz.domain.entities.list

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info (
    val count : Int?,
    val pages : Int?,
    val next : String?,
    val prev : String?
)
