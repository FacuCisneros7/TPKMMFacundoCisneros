package org.firstproject.project.Domain

interface PokemonRepository {
    suspend fun getPokemons(): List<Pokemon>
}