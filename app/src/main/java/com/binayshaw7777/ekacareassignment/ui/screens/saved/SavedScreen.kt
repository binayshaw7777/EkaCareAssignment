package com.binayshaw7777.ekacareassignment.ui.screens.saved

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.binayshaw7777.ekacareassignment.R
import com.binayshaw7777.ekacareassignment.data.remote.response.Article
import com.binayshaw7777.ekacareassignment.ui.components.ArticleCardItem
import com.binayshaw7777.ekacareassignment.utils.Utils.toArticle


@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticleViewModel,
    onReadMore: (Article) -> Unit
) {

    val articles by viewModel.savedArticles.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
            .then(modifier)
    ) {
        if (articles.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.no_saved_article_found_we_ll_show_your_saved_articles_once_you_save_them),
                    style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(8.dp)
            ) {
                items(articles) { article ->
                    ArticleCardItem(
                        articleTitle = article.title,
                        articleDescription = article.description,
                        articleUrl = article.url,
                        articleImageUrl = article.urlToImage,
                        savedAt = article.savedAt,
                        onDelete = {
                            viewModel.deleteArticle(article) {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.article_deleted_successfully),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }, onReadMore = {
                            onReadMore(article.toArticle())
                        }
                    )
                }
            }
        }
    }
}