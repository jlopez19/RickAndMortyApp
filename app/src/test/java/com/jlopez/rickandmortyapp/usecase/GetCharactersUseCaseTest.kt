package com.jlopez.rickandmortyapp.usecase

import com.jlopez.rickandmortyapp.domain.model.Characters
import com.jlopez.rickandmortyapp.domain.repository.CharacterRepository
import com.jlopez.rickandmortyapp.domain.usecase.GetCharactersUseCase
import com.jlopez.rickandmortyapp.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class GetCharactersUseCaseTest {

    @RelaxedMockK
    private lateinit var characterRepository: CharacterRepository
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getCharactersUseCase = GetCharactersUseCase(characterRepository)
    }

    @Test
    fun `Given is empty or a valid name, When the repository returns characters, Then return the list of characters`() =
        runTest {
            var mockCharacters = listOf(
                Characters(id = 1, name = "Rick Sanchez", specie = "Human", image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
                Characters(id = 2, name = "Morty Smith", specie = "Human", image = "https://rickandmortyapi.com/api/character/2"),
                Characters(id = 3, name = "Summer Smith", specie = "Human", image = "https://rickandmortyapi.com/api/character/3")
            )
            coEvery { characterRepository.getCharacters(any()) } returns Result.Success(mockCharacters)

            val response = getCharactersUseCase("Summer")

            assertEquals(mockCharacters, response.data)
        }
}