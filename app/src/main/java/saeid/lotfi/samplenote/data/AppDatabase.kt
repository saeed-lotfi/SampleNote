package saeid.lotfi.samplenote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import saeid.lotfi.samplenote.model.NoteDetail

@Database(entities = [NoteDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
