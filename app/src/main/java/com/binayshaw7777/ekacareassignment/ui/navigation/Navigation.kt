package com.binayshaw7777.ekacareassignment.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.binayshaw7777.ekacareassignment.ui.screens.detail.DetailScreen
import com.binayshaw7777.ekacareassignment.ui.screens.home.HomeScreen
import com.binayshaw7777.ekacareassignment.ui.screens.saved.SavedScreen
import com.binayshaw7777.ekacareassignment.utils.FadeIn
import com.binayshaw7777.ekacareassignment.utils.FadeOut

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    val screensWithoutNavBar = listOf(
        "${Screens.Detail.route}/{newsUrl}"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                backStackEntry = backStackEntry,
                screensWithoutNavBar = screensWithoutNavBar,
                navController = navController
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues),
            enterTransition = { FadeIn },
            exitTransition = { FadeOut },
            popEnterTransition = { FadeIn },
            popExitTransition = { FadeOut }
        ) {

            composable(
                route = Screens.Home.route
            ) {
                HomeScreen(navController = navController, viewModel = hiltViewModel())
            }

            composable(
                route = Screens.Saved.route
            ) {
                SavedScreen()
            }

            composable(
                route = "${Screens.Detail.route}/{newsUrl}",
                arguments = listOf(navArgument("newsUrl") { type = NavType.StringType })
            ) { backStackEntry ->
                val newsUrl =
                    backStackEntry.arguments?.getString("newsUrl") ?: "https://www.google.com"
                DetailScreen(newsUrl = newsUrl)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    backStackEntry: State<NavBackStackEntry?>,
    screensWithoutNavBar: List<String>,
    navController: NavHostController
) {
    if (backStackEntry.value?.destination?.route !in screensWithoutNavBar) {
        NavigationBar(
            modifier = modifier
        ) {
            BottomNavItem().bottomNavigationItems()
                .forEachIndexed { index, navigationItem ->

                    val isSelected =
                        backStackEntry.value?.destination?.route == navigationItem.route

                    NavigationBarItem(
                        selected = isSelected,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                if (isSelected) {
                                    navigationItem.selectedIcon
                                } else {
                                    navigationItem.unselectedIcon
                                },
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            val currentDestination = backStackEntry.value?.destination?.route
                            if (navigationItem.route != currentDestination) {
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
        }
    }
}