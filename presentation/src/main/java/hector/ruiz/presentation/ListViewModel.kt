package hector.ruiz.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hector.ruiz.domain.entities.list.Results
import hector.ruiz.usecase.usecases.GetCharactersUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        manageError()
    }

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _characterList: MutableLiveData<List<Results?>> = MutableLiveData()
    val characterList: LiveData<List<Results?>>
        get() = _characterList

    private val _errorRequest: MutableLiveData<Boolean> = MutableLiveData()
    val errorRequest: LiveData<Boolean>
        get() = _errorRequest

    fun getCharacterList(pageNumber: Int?) = viewModelScope.launch(exceptionHandler) {
        _isLoading.postValue(true)
        val result = getCharactersUseCase(pageNumber)
        result.data?.results?.let {
            _characterList.postValue(it)
            _isLoading.postValue(false)
        } ?: manageError()
    }

    private fun manageError() {
        _isLoading.postValue(false)
        if (_characterList.value.isNullOrEmpty()) {
            _errorRequest.postValue(true)
        }
    }
}
