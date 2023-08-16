package com.procore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.procore.data.repository.PokemonRepository
import com.procore.domain.PokemonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() = viewModelScope.launch {
        _uiState.update { it.copy(type = UiState.Type.LOADING) }
        pokemonRepository.getPokemon()
            .onSuccess { pokemons ->
                _uiState.update { it.copy(type = UiState.Type.SUCCESS, pokemonList = pokemons) }
            }
            .onFailure { _uiState.update { it.copy(type = UiState.Type.ERROR) } }
    }

    data class UiState(
        val pokemonList: List<PokemonModel> = emptyList(),
        val type: Type = Type.IDLE
    ) {
        enum class Type {
            IDLE, SUCCESS, ERROR, LOADING
        }
    }
}
