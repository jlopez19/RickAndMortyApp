package com.jlopez.rickandmortyapp.data.remote.dto
import com.jlopez.rickandmortyapp.domain.model.Character

data class CharacterDto(
    val info: InfoCharacter,
    val results: List<Character>
)
