package saeid.lotfi.samplenote.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import saeid.lotfi.samplenote.model.NoteDetail

@Composable
fun NotesList(
    contentPadding: PaddingValues,
    noteViewModel: NoteViewModel = hiltViewModel(),
    noteClicked: (Long?) -> Unit,
) {
    val notes = noteViewModel.notes.collectAsStateWithLifecycle()

    LazyVerticalStaggeredGrid(
        contentPadding = contentPadding,
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(notes.value.size) { note ->
                NoteItem(
                    note = notes.value[note],
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    onNoteClicked = { noteId ->
                        noteClicked.invoke(noteId)
                    },
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
fun NoteItem(note: NoteDetail, modifier: Modifier, onNoteClicked: (Long?) -> Unit) {
    Column(
        modifier = modifier.clickable {
            onNoteClicked.invoke(note.id)
        },
    ) {
        Text(
            text = note.title,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = note.description,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
fun NoteItemPreview() {
    NoteItem(
        note = NoteDetail(
            title = "Title",
            description = "Description",
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
        onNoteClicked = {},
    )
}
