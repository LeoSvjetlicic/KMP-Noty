package org.example.project

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.database.getNoteDatabase

fun MainViewController() = ComposeUIViewController {
    val dao = remember { getNoteDatabase().getDao() }

    App(dao)
}
