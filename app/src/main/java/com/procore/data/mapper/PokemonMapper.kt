package com.procore.data.mapper

import com.procore.data.remote.dto.PokemonDataDto
import com.procore.data.remote.dto.PokemonDto
import com.procore.domain.PokemonModel

fun PokemonDataDto.toPokemonModel(): List<PokemonModel> {
    return this.data.map { it.toPokemonModel() }
}

fun PokemonDto.toPokemonModel(): PokemonModel {
    return PokemonModel(
        id = this.id,
        small = this.images.small,
        large = this.images.large
    )
}
