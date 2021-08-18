package hector.ruiz.domain.entities.list

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Characters (
    val info : Info?,
    val results : List<Results?>?
)
