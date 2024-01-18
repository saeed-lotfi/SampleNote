package saeid.lotfi.samplenote.ui.tags

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun Tags(
    modifier: Modifier,
    contentPadding: PaddingValues,
    viewModel: TagViewModel = hiltViewModel()
) {

    val tags = viewModel.getAllTags.collectAsStateWithLifecycle()

    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        content = {
            items(tags.value.size) {
                TagItem(
                    modifier = Modifier,
                    text = tags.value[it].tagTitle,
                    color = tags.value[it].tagColor,
                    onTailClicked = {
                        viewModel.deleteTag(tags.value[it].tagId)
                    }
                )
            }
        },
    )
}

@Composable
fun TagItem(
    modifier: Modifier,
    text: String,
    color: Long,
    onTailClicked: () -> Unit,
) {
    AssistChip(
        modifier = modifier,
        onClick = {
        },
        colors = ChipColors(
            containerColor = Color.White,
            labelColor = Color.Black,
            leadingIconContentColor = Color.Transparent,
            trailingIconContentColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            disabledLabelColor = Color.Transparent,
            disabledLeadingIconContentColor = Color.Transparent,
            disabledTrailingIconContentColor = Color.Transparent

        ),
        label = { Text(text) },
        leadingIcon = {
            androidx.compose.material3.Surface(
                color = Color(color),
                contentColor = Color.White,
                shape = androidx.compose.foundation.shape.CircleShape,
                modifier = Modifier.size(InputChipDefaults.AvatarSize)
            ) {
            }
        },
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(InputChipDefaults.AvatarSize)
                    .clickable {
                        onTailClicked.invoke()
                    }

            )
        },
    )
}

@Preview
@Composable
fun TagItemPreview() {
    TagItem(
        modifier = Modifier,
        text = "Tag",
        color = 0xFF9CCC65,
        onTailClicked = {}
    )
}