package com.procore.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.procore.domain.PokemonModel
import com.procore.presentation.viewmodel.PokemonViewModel
import com.procore.ui.theme.AppTheme

@Composable
fun PokemonScreen(viewModel: PokemonViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    when (uiState.type) {
        PokemonViewModel.UiState.Type.SUCCESS -> PokemonScreen(uiState.pokemonList)
        PokemonViewModel.UiState.Type.ERROR -> Text(text = "Error State")
        PokemonViewModel.UiState.Type.LOADING -> LoadingState()
        else -> Unit
    }
}

@Composable
private fun PokemonScreen(pokemonModels: List<PokemonModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4)
    ) {
        items(pokemonModels) {
            AsyncImage(
                model = it.small,
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun PokemonScreenPreview() {
    val imageUrl = "https://images.pokemontcg.io/dp3/logo.png"
    val pokemonList = listOf(PokemonModel(id = "1", imageUrl, imageUrl))
    AppTheme {
        PokemonScreen(pokemonList)
    }
}
