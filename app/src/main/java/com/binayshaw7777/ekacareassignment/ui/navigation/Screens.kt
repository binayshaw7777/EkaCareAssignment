package com.binayshaw7777.ekacareassignment.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.binayshaw7777.ekacareassignment.data.remote.response.Article
import com.binayshaw7777.ekacareassignment.utils.Constants
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder

sealed class Screens(val route: String) {
    data object Home : Screens(Constants.HOME_ROUTE)
    data object Saved : Screens(Constants.SAVED_ROUTE)
    data object Detail : Screens(Constants.NEWS_DETAILS_ROUTE) {
        fun createRoute(articles: Article): String {
            val json = Json.encodeToString(articles)
            return "${Constants.NEWS_DETAILS_ROUTE}/${URLEncoder.encode(json, "UTF-8")}"
        }
    }
}

data class BottomNavItem(
    val route: String = Constants.HOME_ROUTE,
    val selectedIcon: ImageVector = Icons.Filled.Home,
    val unselectedIcon: ImageVector = Icons.Outlined.Home,
    val label: String = "Home"
) {
    fun bottomNavigationItems() : List<BottomNavItem> {
        return listOf(
            BottomNavItem(
                label = "Home",
                unselectedIcon = Icons.Outlined.Home,
                selectedIcon = Icons.Filled.Home,
                route = Screens.Home.route,
            ),
            BottomNavItem(
                label = "Search",
                selectedIcon = Icons.Filled.Bookmark,
                unselectedIcon = Icons.Outlined.BookmarkBorder,
                route = Screens.Saved.route
            )
        )
    }
}

