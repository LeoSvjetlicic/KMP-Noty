package org.example.project.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.database.Note
import org.ls.notey.utils.SALMON

@Composable
fun MainContent(
    notes: List<Note>,
    modifier: Modifier = Modifier,
    onDeleteClick: (Long) -> Unit,
    onPokemonClick: () -> Unit,
    onItemClick: (Long) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier) {
            items(notes) {
                NoteCard(
                    modifier = Modifier.padding(bottom = 8.dp),
                    note = it,
                    onDeleteClick = onDeleteClick,
                    onItemClick = onItemClick
                )
            }
        }
        Button(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = { onPokemonClick() },
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp,
                hoveredElevation = 0.dp,
                focusedElevation = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = SALMON,
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "Catch them all", color = Color.White)
        }
    }
}
