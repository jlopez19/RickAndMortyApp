package com.jlopez.rickandmortyapp.data.remote.dto

data class InfoCharacter(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
