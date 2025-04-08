package com.jlopez.rickandmortyapp.data.mapper

import com.jlopez.rickandmortyapp.data.remote.dto.CharacterDetailDto
import com.jlopez.rickandmortyapp.data.remote.dto.CharacterDto
import com.jlopez.rickandmortyapp.domain.model.CharacterDetail
import com.jlopez.rickandmortyapp.domain.model.Characters
import javax.inject.Inject

class CharacterMapper @Inject constructor() {

    fun mapToDomain(characterDetail: CharacterDetailDto): CharacterDetail {
        return CharacterDetail(
            id = characterDetail.id,
            name = characterDetail.name,
            status = characterDetail.status,
            species = characterDetail.species,
            type = characterDetail.type,
            gender = characterDetail.gender,
            image = characterDetail.image,
            url = characterDetail.url,
            created = characterDetail.created
        )
    }

    fun mapToDomain(response: CharacterDto): List<Characters> {
        val resultCharacter = response.results.mapIndexed { _, character ->
            Characters(
                id = character.id,
                name = character.name,
                specie = character.species,
                image = character.image
            )
        }
        return resultCharacter
    }
}
