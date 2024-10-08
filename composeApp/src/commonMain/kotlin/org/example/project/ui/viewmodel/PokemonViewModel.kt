package org.example.project.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.domain.repository.PokemonRepo
import org.example.project.ui.PokeListUiState
import org.example.project.ui.PokeUiEvent
import org.example.project.utils.Resource

class PokemonViewModel(private val repo: PokemonRepo) : ViewModel() {
    val uiState = mutableStateOf(PokeListUiState())

    init {
        onEvent(PokeUiEvent.GetPokemonList)
    }

    fun onEvent(event: PokeUiEvent) {
        when (event) {
            is PokeUiEvent.GetPokemonList -> {
                getPokemonList()
            }
        }
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            when (val response = repo.getPokemonList()) {
                is Resource.Success -> {
                    uiState.value = uiState.value.copy(
                        data = response.data.results,
                        loading = false,
                        error = ""
                    )
                }

                is Resource.Loading -> {
                    uiState.value = uiState.value.copy(
                        loading = true,
                    )
                }

                is Resource.Error -> {
                    uiState.value = uiState.value.copy(
                        loading = false,
                        error = response.exception.message ?: "Something went wrong"
                    )
                }
            }
        }
    }
}

