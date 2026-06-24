package org.firstproject.project.Domain

class GetPokemonsUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(): List<Pokemon> {
        return repository.getPokemons()
    }
}