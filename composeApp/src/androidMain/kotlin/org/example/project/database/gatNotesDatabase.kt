package org.example.project.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun getNotesDatabase(context: Context): NoteDatabase {
    val dbFile = context.getDatabasePath("notes.db")
    return Room.databaseBuilder<NoteDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    ).setDriver(BundledSQLiteDriver()).build()
}
