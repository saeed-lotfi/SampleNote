package saeid.lotfi.samplenote.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import saeid.lotfi.samplenote.model.TagModel

@Dao
interface TagDao {

    @Upsert
    suspend fun insert(tagModel: TagModel): Long

    @Query("DELETE FROM tagmodel WHERE tagId = :tagId")
    suspend fun delete(tagId: Long)

    @Query("SELECT * FROM tagmodel")
    fun getAllTags(): Flow<List<TagModel>>
}