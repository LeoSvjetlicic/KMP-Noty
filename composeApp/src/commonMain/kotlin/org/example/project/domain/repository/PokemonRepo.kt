package org.example.project.domain.repository

import org.example.project.data.model.PokemonResponse
import org.example.project.utils.Resource

interface PokemonRepo {
    suspend fun getPokemonList(): Resource<PokemonResponse>
}
