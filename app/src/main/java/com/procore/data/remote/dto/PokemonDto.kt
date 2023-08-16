package com.procore.data.remote.dto

import com.squareup.moshi.Json

data class PokemonDataDto(
    @Json(name = "data")
    val data: List<PokemonDto>
)

data class PokemonDto(
    @Json(name = "id")
    val id: String,
    @Json(name = "images")
    val images: ImagesDto
)

data class ImagesDto(
    @Json(name = "small")
    val small: String,
    @Json(name = "large")
    val large: String
)
