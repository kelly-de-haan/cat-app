package nl.kellydehaan.catapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import nl.kellydehaan.catapp.data.model.CatBreed
import nl.kellydehaan.catapp.data.repository.CatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BreedsUiState(
    val breeds: List<CatBreed> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 0,
    val hasReachedEnd: Boolean = false
)

class BreedsViewModel(
    private val repository: CatRepository = CatRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(BreedsUiState())
    val uiState: StateFlow<BreedsUiState> = _uiState.asStateFlow()

    init {
        loadBreeds()
    }

    fun loadBreeds() {
        if (_uiState.value.isLoading) return
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            repository.getBreeds(page = 0)
                .onSuccess { breeds ->
                    _uiState.update {
                        it.copy(
                            breeds = breeds,
                            isLoading = false,
                            currentPage = 0,
                            hasReachedEnd = breeds.isEmpty()
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "An unexpected error occurred"
                        )
                    }
                }
        }
    }

    /** Called when the list scrolls near the bottom — loads the next page. */
    fun loadMoreBreeds() {
        val state = _uiState.value
        if (state.isLoadingMore || state.hasReachedEnd || state.isLoading) return

        val nextPage = state.currentPage + 1
        _uiState.update { it.copy(isLoadingMore = true) }

        viewModelScope.launch {
            repository.getBreeds(page = nextPage)
                .onSuccess { newBreeds ->
                    _uiState.update {
                        it.copy(
                            breeds = it.breeds + newBreeds,
                            isLoadingMore = false,
                            currentPage = nextPage,
                            hasReachedEnd = newBreeds.isEmpty()
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoadingMore = false,
                            error = throwable.message ?: "Failed to load more breeds"
                        )
                    }
                }
        }
    }

    fun dismissError() = _uiState.update { it.copy(error = null) }
}
