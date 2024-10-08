package org.example.project.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.project.ui.main.viewstates.NoteViewState

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val content: String,
    val createdAt: String
) {
    companion object {
        val EMPTY = Note(0L, "", "", "")
    }
}

fun Note.toNoteViewState(): NoteViewState =
    NoteViewState(
        id = this.id,
        topic = this.title,
        description = this.content,
        createdAt = this.createdAt
    )

