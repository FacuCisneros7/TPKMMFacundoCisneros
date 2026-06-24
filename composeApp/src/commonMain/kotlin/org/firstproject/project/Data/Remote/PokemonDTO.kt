package org.firstproject.project.Data.Remote

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListDto(
    val results: List<PokemonItemDto>
)

@Serializable
data class PokemonItemDto(
    val name: String,
    val url: String
)