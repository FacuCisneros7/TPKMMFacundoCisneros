package org.firstproject.project.UI.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.firstproject.project.Domain.GetPokemonsUseCase
import org.firstproject.project.UI.ScreenState

class PokemonViewModel(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState: StateFlow<ScreenState> = _screenState

    init {
        loadPokemons()
    }

    private fun loadPokemons() {
        viewModelScope.launch {
            try {
                val pokemons = getPokemonsUseCase()
                _screenState.value = ScreenState.ShowPokemons(pokemons)
            } catch (e: Exception) {
                _screenState.value = ScreenState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}