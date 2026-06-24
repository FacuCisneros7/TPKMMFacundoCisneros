package org.firstproject.project.Data.Local

import org.firstproject.project.Domain.Pokemon
import org.firstproject.project.PokemonDatabase

class PokemonLocalDataSource(driverFactory: DatabaseDriverFactory) {
    private val database = PokemonDatabase(driverFactory.createDriver())
    private val queries = database.pokemonQueries

    fun getAllPokemons(): List<Pokemon> {
        return queries.getAllPokemons().executeAsList().map {
            Pokemon(
                id = it.id,
                name = it.name,
                description = it.description,
                imageUrl = it.imageUrl
            )
        }
    }

    fun insertPokemons(pokemons: List<Pokemon>) {
        pokemons.forEach {
            queries.insertPokemon(
                id = it.id.toLong(),
                name = it.name,
                description = it.description!!,
                imageUrl = it.imageUrl
            )
        }
    }

    fun deleteAllPokemons() {
        queries.deleteAllPokemons()
    }
}