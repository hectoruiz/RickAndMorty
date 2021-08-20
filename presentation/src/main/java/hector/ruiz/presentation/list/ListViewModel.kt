package hector.ruiz.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hector.ruiz.domain.entities.list.Results
import hector.ruiz.usecase.usecases.AddFavoriteUseCase
import hector.ruiz.usecase.usecases.GetCharactersUseCase
import hector.ruiz.usecase.usecases.RemoveFavoriteUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
) : ViewModel() {

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

    private val _favoriteResult: MutableLiveData<FavoriteResult> = MutableLiveData()
    val favoriteResult: LiveData<FavoriteResult>
        get() = _favoriteResult

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

    fun addFavorite(characterId: Int) = viewModelScope.launch(exceptionHandler) {
        val result = addFavoriteUseCase(characterId)
        if (result) {
            manageResultFavorite(FavoriteResult.ADD_SUCCESS)
        } else manageResultFavorite(FavoriteResult.ERROR)
    }

    fun removeFavorite(characterId: Int) = viewModelScope.launch(exceptionHandler) {
        val result = removeFavoriteUseCase(characterId)
        if (result) {
            manageResultFavorite(FavoriteResult.REMOVE_SUCCESS)
        } else manageResultFavorite(FavoriteResult.ERROR)
    }

    private fun manageResultFavorite(favoriteResult: FavoriteResult) {
        _favoriteResult.postValue(favoriteResult)
    }

    private fun manageError() {
        _isLoading.postValue(false)
        if (_characterList.value.isNullOrEmpty()) {
            _errorRequest.postValue(true)
        }
    }

    enum class FavoriteResult {
        ADD_SUCCESS,
        REMOVE_SUCCESS,
        ERROR
    }
}
