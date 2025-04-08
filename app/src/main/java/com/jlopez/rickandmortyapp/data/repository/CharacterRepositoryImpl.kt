package com.jlopez.rickandmortyapp.data.repository

import com.jlopez.rickandmortyapp.data.mapper.CharacterMapper
import com.jlopez.rickandmortyapp.data.remote.datasource.RemoteDataSource
import com.jlopez.rickandmortyapp.domain.repository.CharacterRepository
import com.jlopez.rickandmortyapp.domain.model.CharacterDetail
import com.jlopez.rickandmortyapp.domain.model.Characters
import com.jlopez.rickandmortyapp.utils.Result
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val characterMapper: CharacterMapper
) : CharacterRepository {

    override suspend fun getCharacters(): Result<List<Characters>> {
        try {
            val response = remoteDataSource.getCharacters()
            return Result.Success(characterMapper.mapToDomain(response))
        } catch (e: HttpException) {
            return   Result.Error(
                message = e.message(),
                data = null
            )
        } catch (e: IOException) {
                 return Result.Error(
                message = e.message.toString(),
                data = null
            )
        }

    }

    override suspend fun getCharacterDetails(characterId: Int): Result<CharacterDetail> {
        try {
            val response = remoteDataSource.getCharacterDetails(characterId)
            return Result.Success(characterMapper.mapToDomain(response))
        } catch (e: HttpException) {
            return   Result.Error(
                message = e.message(),
                data = null
            )
        } catch (e: IOException) {
            return Result.Error(
                message = e.message.toString(),
                data = null
            )
        }
    }
}
