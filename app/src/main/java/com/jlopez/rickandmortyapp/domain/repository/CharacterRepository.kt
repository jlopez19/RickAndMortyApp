package com.jlopez.rickandmortyapp.domain.repository

import com.jlopez.rickandmortyapp.domain.model.CharacterDetail
import com.jlopez.rickandmortyapp.domain.model.Characters
import com.jlopez.rickandmortyapp.utils.Result

interface CharacterRepository {
    suspend fun getCharacters(): Result<List<Characters>>
    suspend fun getCharacterDetails(characterId: Int): Result<CharacterDetail>

}