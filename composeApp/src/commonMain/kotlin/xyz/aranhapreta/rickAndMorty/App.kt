package xyz.aranhapreta.rickAndMorty

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups2
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material.icons.outlined.Groups2
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material.icons.outlined.TravelExplore
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import dev.chrisbanes.haze.rememberHazeState
import xyz.aranhapreta.feature.episodes.presentation.EpisodesScreen
import xyz.aranhapreta.feature.locations.presentation.LocationsScreen
import xyz.aranhapreta.rickAndMorty.di.Koin
import xyz.aranhapreta.rickAndMorty.feature.characters.presentation.CharactersScreen
import xyz.aranhapreta.theme.AppTheme

@OptIn(ExperimentalHazeMaterialsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App() {
    Koin {
        AppTheme {
            val navController = rememberNavController()
            val hazeState = rememberHazeState()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentScreen by derivedStateOf {
                Screen.entries.firstOrNull { screen ->
                    screen.name == backStackEntry?.destination?.route
                } ?: Screen.Characters
            }
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .hazeEffect(state = hazeState, style = HazeMaterials.ultraThin())
                            .fillMaxWidth(),
                        title = {
                            Text(currentScreen.name)
                        }
                    )
                },
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier
                            .hazeEffect(state = hazeState, style = HazeMaterials.ultraThin()),
                        containerColor = Color.Transparent,
                    ) {
                        Screen.entries.forEachIndexed { index, destination ->
                            val selected by derivedStateOf {
                                currentScreen == destination
                            }
                            NavigationBarItem(
                                selected = selected,
                                onClick = { navController.navigate(route = destination.name) },
                                icon = {
                                    Icon(screen = destination, selected = selected)
                                },
                                label = { Text(destination.name) }
                            )
                        }
                    }
                },
            ) { contentPadding ->
                val layoutDirection = LocalLayoutDirection.current
                val paddingValuesWithoutNavigation = PaddingValues(
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    top = 0.dp,
                    end = contentPadding.calculateEndPadding(layoutDirection),
                    bottom = 0.dp
                )
                AppNavHost(
                    navController = navController,
                    startDestination = Screen.entries.first(),
                    modifier = Modifier
                        .padding(paddingValuesWithoutNavigation)
                        .hazeSource(state = hazeState),
                    contentPadding = contentPadding,
                )
            }
        }
    }
}

@Composable
private fun Icon(screen: Screen, selected: Boolean) {
    val icon = when (screen) {
        Screen.Characters -> if (selected) {
            Icons.Filled.Groups2
        } else {
            Icons.Outlined.Groups2
        }

        Screen.Locations -> if (selected) {
            Icons.Filled.TravelExplore
        } else {
            Icons.Outlined.TravelExplore
        }

        Screen.Episodes -> if (selected) {
            Icons.Filled.Subscriptions
        } else {
            Icons.Outlined.Subscriptions
        }
    }
    Icon(
        imageVector = icon,
        contentDescription = screen.name,
        modifier = Modifier.size(30.dp),
    )
}

@Composable
private fun AppNavHost(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.name
    ) {
        Screen.entries.forEach { destination ->
            composable(destination.name) {
                when (destination) {
                    Screen.Characters -> CharactersScreen(contentPadding)
                    Screen.Locations -> LocationsScreen()
                    Screen.Episodes -> EpisodesScreen()
                }
            }
        }
    }
}
