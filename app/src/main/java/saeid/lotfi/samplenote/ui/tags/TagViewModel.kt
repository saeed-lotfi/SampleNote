package saeid.lotfi.samplenote.ui.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import saeid.lotfi.samplenote.model.TagModel
import saeid.lotfi.samplenote.repository.TagRepository
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(
    private val tagRepository: TagRepository
) : ViewModel() {

    val getAllTags =
        tagRepository.getAllTags()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    fun deleteTag(tagId: Long) {
        viewModelScope.launch {
            tagRepository.delete(tagId)
        }
    }

    fun insertOrUpdateTag(tagModel: TagModel) {
        viewModelScope.launch {
            tagRepository.insert(tagModel)
        }
    }

}