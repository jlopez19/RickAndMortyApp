package com.jlopez.rickandmortyapp.data.remote.datasource

import com.jlopez.rickandmortyapp.data.remote.api.ApiService
import javax.inject.Inject

class RemoteDataSource  @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getCharacters() = apiService.getCharacters()

    suspend fun getCharacterDetails(id: Int) = apiService.getCharacterDetails(id)

}