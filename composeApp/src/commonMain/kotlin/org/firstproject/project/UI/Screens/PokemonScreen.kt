package org.firstproject.project.UI.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.firstproject.project.Data.Local.PokemonLocalDataSource
import org.firstproject.project.Data.Remote.PokemonApiClient
import org.firstproject.project.Data.Repository.PokemonRepositoryImpl
import org.firstproject.project.Domain.GetPokemonsUseCase
import org.firstproject.project.Domain.Pokemon
import org.firstproject.project.UI.Componentes.PokemonItem
import org.firstproject.project.UI.ScreenState
import org.firstproject.project.UI.ViewModel.PokemonViewModel

@Composable
fun PokemonScreen(localDataSource: PokemonLocalDataSource) {
    val viewModel = viewModel {
        PokemonViewModel(GetPokemonsUseCase(PokemonRepositoryImpl(PokemonApiClient(), localDataSource)))
    }

    val state by viewModel.screenState.collectAsState()

    Box(Modifier.fillMaxSize()){
        Column(
            Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "POKEDEX", color = Color.Red, fontSize = 40.sp, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(12.dp))

            when (state) {
                ScreenState.Loading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
                is ScreenState.ShowPokemons -> PokemonList((state as ScreenState.ShowPokemons).list)
                is ScreenState.Error -> Text((state as ScreenState.Error).message)
            }
        }
    }

}

@Composable
fun PokemonList(pokemons: List<Pokemon>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(pokemons) { pokemon ->
            PokemonItem(pokemon)
        }
    }
}

