package org.example.project.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getNoteDatabase():NoteDatabase{
    val dbFile = NSHomeDirectory() + "/people.db"
    return Room.databaseBuilder<NoteDatabase>(
        name = dbFile,
        factory = {NoteDatabase::class.instantiateImpl()}
    ).setDriver(BundledSQLiteDriver()).build()
}
