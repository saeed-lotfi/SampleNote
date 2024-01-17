package saeid.lotfi.samplenote.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import saeid.lotfi.samplenote.model.NoteDetail

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(noteDetail: NoteDetail): Long

    @Query("SELECT * FROM notedetail")
    fun getAll(): Flow<List<NoteDetail>>
}
