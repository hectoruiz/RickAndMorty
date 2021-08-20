package hector.ruiz.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hector.ruiz.domain.entities.details.LocationDetails
import hector.ruiz.domain.entities.list.Results
import hector.ruiz.usecase.usecases.GetCharacterUseCase
import hector.ruiz.usecase.usecases.GetLocationDetailsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getLocationDetailsUseCase: GetLocationDetailsUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
) :
    ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        manageError()
    }

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _locationDetails: MutableLiveData<LocationDetails?> = MutableLiveData()
    val locationDetails: LiveData<LocationDetails?>
        get() = _locationDetails

    private val _resident: MutableLiveData<Results> = MutableLiveData()
    val resident: LiveData<Results>
        get() = _resident

    private val _errorRequest: MutableLiveData<Boolean> = MutableLiveData()
    val errorRequest: LiveData<Boolean>
        get() = _errorRequest

    fun getLocationDetails(locationUrl: String) = viewModelScope.launch(exceptionHandler) {
        _isLoading.postValue(true)
        getLocationId(locationUrl)?.let { locationId ->
            val result = getLocationDetailsUseCase(locationId)
            result.data?.let {
                _locationDetails.postValue(it)
                _isLoading.postValue(false)
            } ?: manageError()
        }
    }

    private fun getLocationId(locationUrl: String): Int? {
        val locationId = locationUrl.split("/").last()
        return try {
            locationId.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }

    fun getAllResidents(residents: List<String?>?) = viewModelScope.launch(exceptionHandler) {
        _isLoading.postValue(true)
        residents?.forEach { it ->
            it?.let { characterUrl ->
                getLocationId(characterUrl)?.let { characterId ->
                    val result = getCharacterUseCase(characterId)
                    result.data?.let { resident ->
                        _resident.postValue(resident)
                        _isLoading.postValue(false)
                    } ?: manageError()
                }
            }
        }
    }

    private fun manageError() {
        _isLoading.postValue(false)
        _errorRequest.postValue(true)
    }
}
