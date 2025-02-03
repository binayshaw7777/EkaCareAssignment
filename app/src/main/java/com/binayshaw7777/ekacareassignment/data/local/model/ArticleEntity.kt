package com.binayshaw7777.ekacareassignment.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "article")
@Serializable
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "url_to_image") val urlToImage: String?,
    @ColumnInfo(name = "savedAt") val savedAt: Long = System.currentTimeMillis()
)