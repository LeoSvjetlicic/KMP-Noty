package org.example.project.ui.main.viewstates

data class NoteViewState(
    val id: Long = 0L,
    val topic: String = "",
    val description: String = "",
    val createdAt: String = "",
) {
    companion object {
        val EMPTY = NoteViewState()
    }
}
