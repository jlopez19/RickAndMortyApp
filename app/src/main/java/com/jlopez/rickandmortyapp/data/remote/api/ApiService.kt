package com.jlopez.rickandmortyapp.data.remote.api

import com.jlopez.rickandmortyapp.data.remote.dto.CharacterDetailDto
import com.jlopez.rickandmortyapp.data.remote.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(@Query("name") name: String? = null): CharacterDto

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): CharacterDetailDto
}