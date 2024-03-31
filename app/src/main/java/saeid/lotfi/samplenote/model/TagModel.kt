package saeid.lotfi.samplenote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TagModel(
    @PrimaryKey(autoGenerate = true)
    val tagId: Long = 0,
    val tagTitle: String,
    val tagColor: Long,
)
