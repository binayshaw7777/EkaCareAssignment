package com.binayshaw7777.ekacareassignment.ui.screens.detail

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.binayshaw7777.ekacareassignment.data.remote.response.Article
import timber.log.Timber

@Composable
fun DetailScreen(modifier: Modifier = Modifier, article: Article) {

    LaunchedEffect(Unit) {
        Timber.d("Article Passed: $article")
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .then(modifier),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

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
        modifier = Modifier.fillMaxSize(),
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