package com.binayshaw7777.ekacareassignment.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.binayshaw7777.ekacareassignment.data.remote.response.Article
import com.binayshaw7777.ekacareassignment.domain.model.ChipItem
import com.binayshaw7777.ekacareassignment.ui.components.ArticleCardItem
import com.binayshaw7777.ekacareassignment.ui.screens.home.component.HomeScreenShimmerState
import com.binayshaw7777.ekacareassignment.utils.NetworkResult
import com.binayshaw7777.ekacareassignment.utils.Utils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onArticleClick: (Article) -> Unit
) {

    val newsResponse by viewModel.newsResponse.collectAsState()
    val articles by viewModel.articles.collectAsState()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val pullRefreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }

    var selectedChip by remember { mutableStateOf<ChipItem?>(null) }
    val chipItems by remember { mutableStateOf(Utils.getChipItems()) }


    LaunchedEffect(Unit) {
        if (articles.isEmpty()) {
            viewModel.getNews(chipItems[0].label)
        }
    }

    LaunchedEffect(selectedChip) {
        selectedChip?.let {
            viewModel.getNews(query = it.label)
        }
    }

    Column(modifier = Modifier.then(modifier)) {

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(chipItems) { chip ->
                FilterChip(
                    selected = selectedChip?.id == chip.id,
                    onClick = {
                        selectedChip = if (selectedChip?.id == chip.id) null else chip
                        if (selectedChip == null) {
                            viewModel.getNews(query = chipItems[0].label)
                        }
                    },
                    label = { Text(chip.label) },
                    shape = RoundedCornerShape(100.dp)
                )
            }
        }

        Spacer(Modifier.height(4.dp))


        when (newsResponse) {
            is NetworkResult.Loading -> {
                HomeScreenShimmerState()
            }

            is NetworkResult.Success -> {
                if (articles.isNotEmpty()) {
                    PullToRefreshBox(
                        isRefreshing = isRefreshing,
                        state = pullRefreshState,
                        onRefresh = {
                            isRefreshing = true
                            scope.launch {
                                delay(500)
                                val query = selectedChip?.label ?: chipItems[0].label
                                viewModel.getNews(query)
                                isRefreshing = false
                            }
                        }
                    ) {
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