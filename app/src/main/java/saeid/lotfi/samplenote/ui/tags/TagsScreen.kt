package saeid.lotfi.samplenote.ui.tags

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import saeid.lotfi.samplenote.R
import saeid.lotfi.samplenote.model.TagModel

@Composable
fun TagsScreen(
    modifier: Modifier,
    contentPadding: PaddingValues,
    viewModel: TagViewModel = hiltViewModel(),
) {
    val tags = viewModel.getAllTags.collectAsStateWithLifecycle()

    Tags(
        modifier = modifier,
        contentPadding = contentPadding,
        tags = tags,
        onTagAdded = { tagName ->
            viewModel.insertOrUpdateTag(TagModel(tagTitle = tagName, tagColor = 0xFF9CCC65))
        },
    )
}

@Composable
fun Tags(
    contentPadding: PaddingValues,
    tags: State<List<TagModel>>,
    onTagAdded: (String) -> Unit,
    modifier: Modifier,
) {
    ConstraintLayout(modifier = modifier) {
        val (list, input) = createRefs()

        TagList(
            modifier = Modifier
                .constrainAs(list) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(input.top)
                },
            contentPadding = contentPadding,
            tags = tags,
        )
        TagInput(
            modifier = Modifier.constrainAs(input) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        ) { tagName ->
            onTagAdded.invoke(tagName)
        }
    }
}

@Composable
fun TagList(
    modifier: Modifier,
    contentPadding: PaddingValues,
    tags: State<List<TagModel>>,
) {
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
                        // nothing
                    },
                )
            }
        },
    )
}

@Composable
fun TagInput(
    modifier: Modifier,
    onTagAdded: (String) -> Unit,
) {
    val tagName by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = tagName,
            onValueChange = {
                // nothing
            },
            placeholder = {
                Text(stringResource(R.string.tag))
            },
        )
        IconButton(
            modifier = Modifier,
            onClick = {
                onTagAdded.invoke(tagName)
            },
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(InputChipDefaults.AvatarSize),
            )
        }
    }
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
            disabledTrailingIconContentColor = Color.Transparent,

            ),
        label = { Text(text) },
        leadingIcon = {
            androidx.compose.material3.Surface(
                color = Color(color),
                contentColor = Color.White,
                shape = androidx.compose.foundation.shape.CircleShape,
                modifier = Modifier.size(InputChipDefaults.AvatarSize),
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
                    },

                )
        },
    )
}

@Preview
@Composable
fun TagsPreview() {
    Tags(
        modifier = Modifier,
        contentPadding = PaddingValues(),
        tags = remember {
            mutableStateOf(
                listOf(
                    TagModel(
                        tagTitle = "dd",
                        tagColor = 0xFF9CCC65,
                    ),
                    TagModel(
                        tagTitle = "dd",
                        tagColor = 0xFF9CCC65,
                    ),
                    TagModel(
                        tagTitle = "dd",
                        tagColor = 0xFF9CCC65,
                    ),
                    TagModel(
                        tagTitle = "dd",
                        tagColor = 0xFF9CCC65,
                    ),
                    TagModel(
                        tagTitle = "dd",
                        tagColor = 0xFF9CCC65,
                    ),
                    TagModel(
                        tagTitle = "dd",
                        tagColor = 0xFF9CCC65,
                    ),
                ),
            )
        },
        onTagAdded = {},
    )
}

@Preview
@Composable
fun TagInputPreview() {
    TagInput(
        modifier = Modifier,
        onTagAdded = {},
    )
}

@Preview
@Composable
fun TagItemPreview() {
    TagItem(
        modifier = Modifier,
        text = "Tag",
        color = 0xFF9CCC65,
        onTailClicked = {},
    )
}
