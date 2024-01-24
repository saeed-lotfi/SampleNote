package saeid.lotfi.samplenote.repository

import kotlinx.coroutines.flow.Flow
import saeid.lotfi.samplenote.data.TagDao
import saeid.lotfi.samplenote.model.TagModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TagRepository @Inject constructor(
    private val tagDao: TagDao,
) {
    suspend fun insert(tagModel: TagModel): Long {
        return tagDao.insert(tagModel)
    }

    suspend fun delete(tagId: Long) {
        tagDao.delete(tagId)
    }

    fun getAllTags(): Flow<List<TagModel>> {
        return tagDao.getAllTags()
    }
}
