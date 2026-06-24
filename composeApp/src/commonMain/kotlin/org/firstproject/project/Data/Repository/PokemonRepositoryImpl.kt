package org.firstproject.project.Data.Repository

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.firstproject.project.Data.Local.PokemonLocalDataSource
import org.firstproject.project.Data.Remote.PokemonApiClient
import org.firstproject.project.Domain.Pokemon
import org.firstproject.project.Domain.PokemonRepository

class PokemonRepositoryImpl(
    private val apiClient: PokemonApiClient,
    private val localDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override suspend fun getPokemons(): List<Pokemon> {
        return try {
            coroutineScope {
                val pokemons = apiClient.getPokemons().results.map { dto ->
                    async {
                        val id = dto.url.trimEnd('/').split('/').last().toLong()
                        Pokemon(
                            id = id,
                            name = dto.name.uppercase(),
                            description = apiClient.getDescriptionById(id),
                            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                        )
                    }
                }.awaitAll()

                localDataSource.deleteAllPokemons()
                localDataSource.insertPokemons(pokemons)
                pokemons
            }
        } catch (e: Exception) {
            localDataSource.getAllPokemons()
        }
    }
}