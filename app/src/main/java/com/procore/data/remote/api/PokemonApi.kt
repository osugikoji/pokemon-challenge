package com.procore.data.remote.api

import com.procore.data.remote.dto.PokemonDataDto
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {

    @GET("/v2/cards")
    suspend fun getPokemon(): Response<PokemonDataDto>
}
