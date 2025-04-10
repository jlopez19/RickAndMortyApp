package com.jlopez.rickandmortyapp.domain.usecase

import com.jlopez.rickandmortyapp.domain.repository.CharacterRepository
import com.jlopez.rickandmortyapp.domain.model.CharacterDetail
import com.jlopez.rickandmortyapp.utils.Result

import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(characterId: Int): Result<CharacterDetail>{
        return characterRepository.getCharacterDetails(characterId)
    }
}
