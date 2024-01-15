package saeid.lotfi.samplenote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import saeid.lotfi.samplenote.model.NoteDetail
import saeid.lotfi.samplenote.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
) : ViewModel() {
    suspend fun insert(title: String, description: String) {
        viewModelScope.launch {
            noteRepository.insert(NoteDetail(title = title, description = description))
        }
    }
}
