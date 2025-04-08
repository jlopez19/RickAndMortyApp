package com.jlopez.rickandmortyapp.data.remote.dto

data class CharacterDetailDto (
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String,
    val created: String
)
