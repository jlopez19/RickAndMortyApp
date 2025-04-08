package com.jlopez.rickandmortyapp.domain.usecase

import com.jlopez.rickandmortyapp.domain.model.Characters
import com.jlopez.rickandmortyapp.domain.repository.CharacterRepository
import com.jlopez.rickandmortyapp.utils.Result
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(): Result<List<Characters>> {
        return characterRepository.getCharacters()
    }
}
