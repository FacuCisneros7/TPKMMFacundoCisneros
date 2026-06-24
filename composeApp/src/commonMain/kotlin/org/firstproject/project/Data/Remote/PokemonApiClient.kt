package org.firstproject.project.Data.Remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class PokemonApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getPokemons(): PokemonListDto {
        return client.get("https://pokeapi.co/api/v2/pokemon?limit=20").body()
    }

    suspend fun getDescriptionById(id: Long): String? {
        val response = client.get(
            "https://pokeapi.co/api/v2/pokemon-species/$id"
        ).body<PokemonSpeciesResponse>()

        return response.flavor_text_entries
            .firstOrNull { it.language.name == "es" }
            ?.flavor_text
            ?.replace("\n", " ")
            ?.replace("\u000c", " ")
    }

}