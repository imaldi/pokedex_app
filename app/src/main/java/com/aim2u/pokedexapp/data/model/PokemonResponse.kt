package com.aim2u.pokedexapp.data.model

data class Pokemon (
    val count: Long,
    val next: String,
    val previous: String? = null,
    val results: List<Result>
)

data class Result (
    val name: String,
    val url: String
)
