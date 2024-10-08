package org.example.project.ui.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.ui.PokeListUiState
import org.example.project.ui.PokeUiEvent

@Composable
fun PokeListComposable(uiState: PokeListUiState, onEvent: (PokeUiEvent) -> Unit) {
    if (uiState.loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (uiState.error.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = uiState.error)
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiState.data.size) {
                Row(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(20.dp),
                    horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Top
                ) {
                    Text(text = uiState.data[it].name)
                }
                if (it != uiState.data.size - 1) {
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}
