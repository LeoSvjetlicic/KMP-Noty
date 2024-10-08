package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.project.data.PokemonRepositoryImpl
import org.example.project.database.Note
import org.example.project.database.NoteDAO
import org.example.project.database.toNoteViewState
import org.example.project.expects.ApiImplementation
import org.example.project.ui.details.DetailsScreen
import org.example.project.ui.main.MainContent
import org.example.project.ui.main.TopBar
import org.example.project.ui.navigation.DetailsRoute
import org.example.project.ui.pokemon.PokeListComposable
import org.example.project.ui.viewmodel.DetailsViewModel
import org.example.project.ui.viewmodel.NoteyViewModel
import org.example.project.ui.viewmodel.PokemonViewModel
import org.example.project.utils.Constants.HOME_ROUTE
import org.example.project.utils.Constants.NOTE_ID
import org.example.project.utils.Constants.POKEMON_ROUTE
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(noteDAO: NoteDAO) {
    MaterialTheme {
        val focusManager = LocalFocusManager.current
        val viewModel: NoteyViewModel = viewModel { NoteyViewModel(noteDAO) }
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val showBackButton by remember {
            derivedStateOf {
                when (navBackStackEntry?.destination?.route) {
                    HOME_ROUTE -> false
                    else -> true
                }
            }
        }
        Scaffold(
            modifier = Modifier.fillMaxSize().clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { focusManager.clearFocus() },
            topBar = {
                TopBar(
                    modifier = Modifier.background(Color.Transparent)
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp),
                    isDetailsScreen = showBackButton,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onAddClick = {
                        navController.navigate(DetailsRoute.createNavigationRoute(-1L))
                    })
            },
            content = { paddingValues ->
                NavHost(
                    modifier = Modifier.padding(paddingValues)
                        .padding(16.dp),
                    navController = navController,
                    startDestination = HOME_ROUTE
                ) {
                    composable(route = HOME_ROUTE) {
                        MainContent(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Transparent),
                            notes = viewModel.noteViewState.value.collectAsState(initial = emptyList()).value,
                            onItemClick = {
                                navController.navigate(DetailsRoute.createNavigationRoute(it))
                            },
                            onDeleteClick = { viewModel.onDeleteClick(it) },
                            onPokemonClick = { navController.navigate(POKEMON_ROUTE) }
                        )
                    }

                    composable(
                        route = DetailsRoute.route,
                        arguments = listOf(navArgument(NOTE_ID) { type = NavType.LongType })
                    ) { navBackStackEntry ->
                        val noteId = navBackStackEntry.arguments?.getLong(NOTE_ID) ?: 0L
                        val detailsViewModel: DetailsViewModel =
                            viewModel { DetailsViewModel(noteDAO, noteId) }
                        DetailsScreen(
                            note = detailsViewModel.note.value.collectAsState(initial = Note.EMPTY).value.toNoteViewState(),
                            modifier = Modifier.fillMaxSize()
                        ) { name, description ->
                            detailsViewModel.saveNote(name, description)
                            navController.popBackStack()
                        }
                    }

                    composable(route = POKEMON_ROUTE) {
                        val pokemonViewModel = viewModel<PokemonViewModel> {
                            PokemonViewModel(PokemonRepositoryImpl(ApiImplementation()))
                        }
                        PokeListComposable(pokemonViewModel.uiState.value, pokemonViewModel::onEvent)
                    }
                }
            }
        )
    }
}
