package com.binayshaw7777.ekacareassignment.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.binayshaw7777.ekacareassignment.ui.components.ArticleCardItem

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {

    val newsResponse by viewModel.newsResponse.collectAsState()
    val articles by viewModel.articles.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    LaunchedEffect(newsResponse) {

    }

    Column(modifier = Modifier.then(modifier)) {
        if (articles.isNotEmpty()) {
            LazyColumn {
                items(articles) { item ->
                    ArticleCardItem(article = item) {
                        // Show another screen TODO
                    }
                }
            }
        }
    }
}