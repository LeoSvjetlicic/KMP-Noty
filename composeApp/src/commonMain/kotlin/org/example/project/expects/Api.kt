package org.example.project.expects

import org.example.project.data.model.PokemonResponse
import org.example.project.utils.Resource


interface Api {

    suspend fun getPokemonList(): Resource<PokemonResponse>
}
