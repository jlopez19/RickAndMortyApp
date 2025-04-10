package com.jlopez.rickandmortyapp.ui.screen.characters

import com.jlopez.rickandmortyapp.domain.model.Characters
import com.jlopez.rickandmortyapp.domain.usecase.GetCharactersUseCase
import com.jlopez.rickandmortyapp.util.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.Test
import kotlin.test.assertEquals
import com.jlopez.rickandmortyapp.utils.Result
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlin.Exception

class CharacterViewModelTest {

    @RelaxedMockK
    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private lateinit var characterViewModel: CharacterViewModel

    @get:Rule
    var rule: TestRule = MainDispatcherRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        characterViewModel = CharacterViewModel(getCharactersUseCase)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given a search query, When a valid character is found, Then update the state with success`() = runTest {
        val fakeCharacters = listOf(
            Characters(id = 1, name = "Rick Sanchez", specie = "Human", image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
            Characters(id = 2, name = "Morty Smith", specie = "Human", image = "https://rickandmortyapi.com/api/character/2"),
            Characters(id = 3, name = "Summer Smith", specie = "Human", image = "https://rickandmortyapi.com/api/character/3")
        )
        coEvery { getCharactersUseCase(any()) } returns Result.Success(fakeCharacters)

        characterViewModel.onSearchQueryChanged("Summer")
        advanceTimeBy(1000)
        advanceUntilIdle()

        val state = characterViewModel.state.value
        assertFalse(state.isLoading)
        assertEquals(fakeCharacters, state.characters)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when getCharacters throws exception, state should show error`() = runTest {

        val exception = Exception("Error desconocido")
        coEvery { getCharactersUseCase.invoke(any()) } throws exception

        characterViewModel.onSearchQueryChanged("Summer")
        advanceTimeBy(1000)
        advanceUntilIdle()

        val state = characterViewModel.state.value
        assertFalse(state.isLoading)
        assertEquals("Error: Error desconocido", state.error.toString())
    }

}