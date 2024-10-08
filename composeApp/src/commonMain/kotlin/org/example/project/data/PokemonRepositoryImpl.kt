package org.example.project.data

import org.example.project.data.model.PokemonResponse
import org.example.project.domain.repository.PokemonRepo
import org.example.project.expects.Api
import org.example.project.utils.Resource

class PokemonRepositoryImpl(private val api: Api) : PokemonRepo {
    override suspend fun getPokemonList(): Resource<PokemonResponse> {
        return api.getPokemonList()
    }
}
