package org.firstproject.project.UI

import org.firstproject.project.Domain.Pokemon

sealed class ScreenState {
    object Loading : ScreenState()
    data class ShowPokemons(val list: List<Pokemon>) : ScreenState()
    data class Error(val message: String) : ScreenState()
}