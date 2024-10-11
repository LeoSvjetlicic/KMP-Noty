package org.example.project.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

@Database(entities = [Note::class], version = 1)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getDao(): NoteDAO
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
@TypeConverters(NoteTypeConverters::class)
expect object BookDatabaseConstructor : RoomDatabaseConstructor<NoteDatabase> {
    override fun initialize(): NoteDatabase

}

class NoteTypeConverters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.decodeFromString(ListSerializer(String.serializer()), value)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Json.encodeToString(ListSerializer(String.serializer()), list)
    }
}
