package org.example.project.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.database.NoteDAO

class NoteyViewModel(private val dao: NoteDAO) : ViewModel() {
    val noteViewState = mutableStateOf(dao.getNotes())

    fun onDeleteClick(id: Long) {
        viewModelScope.launch {
            dao.deleteNote(id)
        }
    }
}
