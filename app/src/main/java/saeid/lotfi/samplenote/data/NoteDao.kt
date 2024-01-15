package saeid.lotfi.samplenote.data

import androidx.room.Dao
import androidx.room.Insert
import saeid.lotfi.samplenote.model.NoteDetail

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(noteDetail: NoteDetail): Long
}
