package saeid.lotfi.samplenote.repository

import kotlinx.coroutines.flow.Flow
import saeid.lotfi.samplenote.data.NoteDao
import saeid.lotfi.samplenote.model.NoteDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    private val noteDao: NoteDao,
) {
    suspend fun insert(noteDetail: NoteDetail): Long {
        return noteDao.insert(noteDetail)
    }

    fun getAll(): Flow<List<NoteDetail>> {
        return noteDao.getAll()
    }
}
