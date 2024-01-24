package saeid.lotfi.samplenote.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Notes
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import saeid.lotfi.samplenote.R
import saeid.lotfi.samplenote.ui.note.NotesList
import saeid.lotfi.samplenote.ui.tags.TagsScreen

@Composable
fun Home(
    onNoteClicked: (Long?) -> Unit,
    modifier: Modifier = Modifier,
) {
    var destination by rememberSaveable { mutableStateOf(Destination.NotesList) }
    Scaffold(
        modifier = modifier,
        topBar = { HomeAppBar(title = stringResource(destination.label)) },
        floatingActionButton = {
            if (destination == Destination.NotesList) {
                FloatingActionButton(onClick = { onNoteClicked.invoke(0) }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Note")
                }
            }
        },
        bottomBar = {
            HomeNavigationBar(
                currentDestination = destination.route,
                onDestinationChanged = { destination = it },
            )
        },
    ) { innerPadding ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = destination.route,
            modifier = modifier,
        ) {
            composable(
                route = Destination.NotesList.route,
                enterTransition = { AnimationConstants.enterTransition },
                exitTransition = { AnimationConstants.exitTransition },
            ) {
                NotesList(
                    contentPadding = innerPadding,
                    noteClicked = onNoteClicked,
                )
            }
            composable(
                route = Destination.Tags.route,
                enterTransition = { AnimationConstants.enterTransition },
                exitTransition = { AnimationConstants.exitTransition },
            ) {
                TagsScreen(
                    contentPadding = innerPadding,
                    modifier = Modifier,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeAppBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
    )
}

private enum class Destination(
    val route: String,
    @StringRes val label: Int,
    val imageVector: ImageVector,
) {
    NotesList(
        route = "notes-list",
        label = R.string.notes,
        imageVector = Icons.AutoMirrored.Outlined.Notes,
    ),
    Tags(
        route = "tags",
        label = R.string.tags,
        imageVector = Icons.AutoMirrored.Outlined.Sort,
    ),
}

@Composable
private fun HomeNavigationBar(
    currentDestination: String,
    onDestinationChanged: (Destination) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        for (destination in Destination.entries) {
            val selected = currentDestination == destination.route
            val label = stringResource(destination.label)
            NavigationBarItem(
                selected = selected,
                onClick = { onDestinationChanged(destination) },
                icon = {
                    Icon(
                        imageVector = destination.imageVector,
                        contentDescription = label,
                    )
                },
                label = {
                    if (selected) {
                        Text(text = label)
                    }
                },
            )
        }
    }
}
