package org.example.project.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.database.Note
import org.example.project.ui.main.NoteCard

@Composable
fun MainContent(
    notes: List<Note>,
    modifier: Modifier = Modifier,
    onDeleteClick: (Long) -> Unit,
    onItemClick: (Long) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(notes) {
            NoteCard(
                modifier = Modifier.padding(bottom = 8.dp),
                note = it,
                onDeleteClick = onDeleteClick,
                onItemClick = onItemClick
            )
        }
    }
}
