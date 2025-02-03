package com.binayshaw7777.ekacareassignment.ui.screens.detail

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.binayshaw7777.ekacareassignment.R
import com.binayshaw7777.ekacareassignment.data.remote.response.Article
import com.binayshaw7777.ekacareassignment.ui.screens.saved.ArticleViewModel
import com.binayshaw7777.ekacareassignment.utils.Utils.shareArticle
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    article: Article,
    viewModel: ArticleViewModel,
    onBackPress: () -> Unit
) {

    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val savedRowId by viewModel.savedRowIdLiveData.observeAsState()


    LaunchedEffect(Unit) {
        viewModel.clearSaveRowId()
        Timber.d("Article Passed: $article")
    }

    LaunchedEffect(savedRowId) {
        savedRowId?.let {
            Toast.makeText(
                context,
                context.getString(R.string.article_was_saved_successfully),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .then(modifier),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Article by: ${article.author}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        article.url?.let { context.shareArticle(it) }
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = "Share"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.saveArticle(article)
                }
            ) {
                Icon(imageVector = Icons.Filled.Bookmark, contentDescription = "Save")
            }
        }
    ) { paddingValues ->
        WebView(newsUrl = article.url ?: "https://google.com", paddingValues)
    }
}


@Composable
fun WebView(
    newsUrl: String,
    paddingValues: PaddingValues
) {
    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        factory = {
            WebView(it).apply {
                this.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                this.webChromeClient = CustomWebChromeClient()
            }
        }, update = {
            it.loadUrl(newsUrl)
        })
}

class CustomWebChromeClient : WebChromeClient() {
    override fun onCloseWindow(window: WebView?) {}
}