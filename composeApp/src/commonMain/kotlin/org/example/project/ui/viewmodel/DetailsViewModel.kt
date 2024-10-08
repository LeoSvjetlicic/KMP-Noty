package org.example.project.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.database.Note
import org.example.project.database.NoteDAO

class DetailsViewModel(
    private val dao: NoteDAO,
    private val id: Long
) : ViewModel() {
    val note = mutableStateOf(
        if (id != -1L) {
            dao.getNoteById(id)
        } else {
            flow {
                Note.EMPTY
            }
        }
    )

    fun saveNote(name: String, description: String) {
        val currentDate = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .toString()

        val note = Note(
            id = if (id == -1L) 0L else id,
            title = name,
            content = description,
            createdAt = currentDate
        )

        viewModelScope.launch {
            if (id == -1L) {
                dao.addNote(note)
            } else {
                dao.updateNote(note)
            }
        }
    }
}
