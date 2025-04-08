package com.jlopez.rickandmortyapp.di

import com.jlopez.rickandmortyapp.data.mapper.CharacterMapper
import com.jlopez.rickandmortyapp.data.remote.RetrofitInstance
import com.jlopez.rickandmortyapp.data.remote.api.ApiService
import com.jlopez.rickandmortyapp.data.remote.datasource.RemoteDataSource
import com.jlopez.rickandmortyapp.data.repository.CharacterRepositoryImpl
import com.jlopez.rickandmortyapp.domain.repository.CharacterRepository
import com.jlopez.rickandmortyapp.domain.usecase.GetCharacterDetailsUseCase
import com.jlopez.rickandmortyapp.domain.usecase.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): ApiService {
        return RetrofitInstance.api
    }

    @Provides
    fun provideCharacterMapper(): CharacterMapper {
        return CharacterMapper()
    }

    @Provides
    fun provideCharacterRepository(
        remoteDataSource: RemoteDataSource,
        characterMapper: CharacterMapper
    ): CharacterRepository {
        return CharacterRepositoryImpl(remoteDataSource, characterMapper)
    }
    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    fun provideGetCharactersUseCase(characterRepository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepository)
    }

    @Provides
    fun provideGetCharacterDetailsUseCase(characterRepository: CharacterRepository): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(characterRepository)
    }
}