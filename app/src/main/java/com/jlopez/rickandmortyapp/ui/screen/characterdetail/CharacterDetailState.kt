package com.jlopez.rickandmortyapp.ui.screen.characterdetail

import com.jlopez.rickandmortyapp.domain.model.CharacterDetail

data class CharacterDetailState(
    val character: CharacterDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
