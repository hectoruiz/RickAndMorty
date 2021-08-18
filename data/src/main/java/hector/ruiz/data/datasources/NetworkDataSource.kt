package hector.ruiz.data.datasources

import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Characters

interface NetworkDataSource {

    fun getListCharacters() : Characters?

    fun getPaginatedListCharacters(pageNumber : Int) : Characters?

    fun getLocation(locationId : Int) : LocationDetails?
}
