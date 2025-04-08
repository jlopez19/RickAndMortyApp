package com.jlopez.rickandmortyapp.data.remote.api

import com.jlopez.rickandmortyapp.data.remote.dto.CharacterDetailDto
import com.jlopez.rickandmortyapp.data.remote.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): CharacterDto

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): CharacterDetailDto
}