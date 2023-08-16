package com.procore.data.repository

import com.procore.data.remote.api.PokemonApi
import com.procore.data.mapper.toPokemonModel
import com.procore.domain.PokemonModel
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonApi: PokemonApi) {

    suspend fun getPokemon(): Result<List<PokemonModel>> {
        return runCatching {
            val result = requireNotNull(pokemonApi.getPokemon().body())
            return Result.success(result.toPokemonModel())
        }.getOrElse { Result.failure(it) }
    }
}
