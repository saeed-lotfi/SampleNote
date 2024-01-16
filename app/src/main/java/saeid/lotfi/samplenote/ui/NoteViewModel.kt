package saeid.lotfi.samplenote.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import saeid.lotfi.samplenote.model.NoteDetail
import saeid.lotfi.samplenote.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    @ApplicationContext private val application: Context,
) : ViewModel() {

    private val _titleInput = MutableStateFlow("")
    val titleInput: StateFlow<String> = _titleInput

    private val _descriptionInput = MutableStateFlow("")
    val descriptionInput: StateFlow<String> = _descriptionInput

    fun insert() {
        if (_titleInput.value.isNotEmpty()) {
            viewModelScope.launch {
                _descriptionInput.value = ""
                _titleInput.value = ""
                noteRepository.insert(
                    NoteDetail(
                        title = titleInput.value,
                        description = _descriptionInput.value,
                    ),
                )

                Toast.makeText(
                    application.applicationContext,
                    "Messages have been saved successfully",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        } else {
            Toast.makeText(
                application.applicationContext,
                "Title is empty",
                Toast.LENGTH_SHORT,
            ).show()
        }
    }

    fun titleChanged(title: String) {
        _titleInput.value = title
    }

    fun descriptionChanged(description: String) {
        _descriptionInput.value = description
    }
}
