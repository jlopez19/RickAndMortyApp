package com.jlopez.rickandmortyapp.ui.screen.characters

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.jlopez.rickandmortyapp.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CharactersState())
    val state: State<CharactersState> = _state

    private val _searchQuery = mutableStateOf("")
    private var searchJob: Job? = null

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val characters = getCharactersUseCase.invoke(name = _searchQuery.value)
                _state.value = _state.value.copy(
                    characters = characters.data ?: emptyList(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Error: ${e.message}"
                )
            }
        }
    }
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(1000)
            getCharacters()
        }
    }
}