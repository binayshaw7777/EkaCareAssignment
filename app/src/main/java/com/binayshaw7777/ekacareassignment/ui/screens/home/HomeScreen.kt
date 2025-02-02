package com.binayshaw7777.ekacareassignment.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.binayshaw7777.ekacareassignment.data.remote.response.Article
import com.binayshaw7777.ekacareassignment.ui.components.ArticleCardItem
import com.binayshaw7777.ekacareassignment.ui.screens.home.component.HomeScreenShimmerState
import com.binayshaw7777.ekacareassignment.utils.NetworkResult

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onArticleClick: (Article) -> Unit
) {

    val newsResponse by viewModel.newsResponse.collectAsState()
    val articles by viewModel.articles.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    Column(modifier = Modifier.then(modifier)) {

        when (newsResponse) {
            is NetworkResult.Loading -> {
                HomeScreenShimmerState()
            }

            is NetworkResult.Success -> {
                if (articles.isNotEmpty()) {
                    LazyColumn(
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(articles) { item ->
                            ArticleCardItem(article = item) {
                                onArticleClick(item)
                            }
                        }
                    }
                }
            }

            is NetworkResult.Error -> {
                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }

            is NetworkResult.Exception -> {
                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }
}