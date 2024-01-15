package saeid.lotfi.samplenote.repository

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
}
