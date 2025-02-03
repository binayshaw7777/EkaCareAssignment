package com.binayshaw7777.ekacareassignment.utils

import android.content.Context
import android.content.Intent
import com.binayshaw7777.ekacareassignment.BuildConfig
import com.binayshaw7777.ekacareassignment.data.local.model.ArticleEntity
import com.binayshaw7777.ekacareassignment.data.remote.response.Article


object Utils {
    fun Context.shareArticle(articleUrl: String) {
        val sendIntent = Intent()
        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey check out this article: $articleUrl" + BuildConfig.APPLICATION_ID
        )
        sendIntent.setType("text/plain")
        startActivity(sendIntent)
    }

    fun ArticleEntity.toArticle(): Article {
        return Article(
            title = this.title,
            description = this.description,
            url = this.url,
            urlToImage = this.urlToImage
        )
    }
}