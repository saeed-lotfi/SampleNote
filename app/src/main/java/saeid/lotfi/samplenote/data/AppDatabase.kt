package saeid.lotfi.samplenote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import saeid.lotfi.samplenote.model.NoteDetail
import saeid.lotfi.samplenote.model.TagModel

@Database(entities = [NoteDetail::class, TagModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun tagDao(): TagDao
}
