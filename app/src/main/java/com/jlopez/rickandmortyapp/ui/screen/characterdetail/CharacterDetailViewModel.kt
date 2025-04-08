package com.jlopez.rickandmortyapp.ui.screen.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlopez.rickandmortyapp.domain.usecase.GetCharacterDetailsUseCase
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import dagger.hilt.android.lifecycle.HiltViewModel
import com.jlopez.rickandmortyapp.utils.Result
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CharacterDetailState())
    var state: State<CharacterDetailState> = _state

    fun getCharacterDetails(characterId: Int) {
        viewModelScope.launch {
            getCharacterDetailsUseCase(characterId).also { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            character = result.data,
                            isLoading = false
                        )
                    }
                    is Result.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false
                        )
                    }
                    is Result.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}