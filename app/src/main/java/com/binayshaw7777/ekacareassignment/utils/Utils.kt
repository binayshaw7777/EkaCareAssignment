package com.binayshaw7777.ekacareassignment.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.binayshaw7777.ekacareassignment.data.local.model.ArticleEntity
import com.binayshaw7777.ekacareassignment.data.remote.response.Article
import com.binayshaw7777.ekacareassignment.domain.model.ChipItem
import java.text.SimpleDateFormat
import java.util.Date


object Utils {
    fun Context.shareArticle(articleUrl: String) {
        val sendIntent = Intent()
        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey check out this article: $articleUrl"
        )
        sendIntent.setType("text/plain")
        startActivity(sendIntent)
    }

    fun ArticleEntity.toArticle(): Article {
        return Article(
            title = this.title,
            description = this.description,
            url = this.url,
            urlToImage = this.urlToImage,
            author = this.author
        )
    }
    private val trendingTopics = listOf(
        "AI",
        "Bitcoin",
        "Climate Change",
        "Space Travel",
        "Quantum Computing",
        "Electric Vehicles",
        "Digital Privacy",
        "Cyber Security",
        "Remote Work",
        "Clean Energy",
        "Tech Layoffs",
        "Cloud Computing",
        "Space Tourism",
        "Machine Learning",
        "Smart Cities",
        "Data Privacy",
        "Green Tech",
        "Web3",
        "Metaverse",
        "Supply Chain"
    )

    fun getChipItems(): List<ChipItem> {
        val items = mutableListOf<ChipItem>()
        trendingTopics.forEachIndexed { index, item ->
            items.add(ChipItem(index, item))
        }
        return items
    }

    @SuppressLint("SimpleDateFormat")
    fun Long.dateFormatter(): String {
        return SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(this)).toString()
    }
}