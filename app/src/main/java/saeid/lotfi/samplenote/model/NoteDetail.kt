package saeid.lotfi.samplenote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
)
