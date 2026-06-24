package org.firstproject.project.Data.Remote
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesResponse(
    val flavor_text_entries: List<FlavorTextEntry>
)

@Serializable
data class FlavorTextEntry(
    val flavor_text: String,
    val language: Language
)

@Serializable
data class Language(
    val name: String
)