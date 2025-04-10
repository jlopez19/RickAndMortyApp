package com.jlopez.rickandmortyapp.ui.screen.characters

import com.jlopez.rickandmortyapp.domain.model.Characters

data class CharactersState(
    val characters: List<Characters>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
