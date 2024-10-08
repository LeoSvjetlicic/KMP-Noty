package org.example.project.ui

import org.example.project.data.model.Pokemon

data class PokeListUiState(
    var data: List<Pokemon> = emptyList(),
    var error: String = "",
    var loading: Boolean = false
)

sealed class PokeUiEvent {
    data object GetPokemonList: PokeUiEvent()
}
