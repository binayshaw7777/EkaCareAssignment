package com.binayshaw7777.ekacareassignment.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.binayshaw7777.ekacareassignment.R
import com.binayshaw7777.ekacareassignment.utils.Utils.dateFormatter
import com.binayshaw7777.ekacareassignment.utils.Utils.shareArticle

@Composable
fun ArticleCardItem(
    articleTitle: String? = null,
    articleDescription: String? = null,
    articleUrl: String? = null,
    articleImageUrl: String? = null,
    savedAt: Long? = null,
    onDelete: () -> Unit = {},
    onReadMore: () -> Unit
) {
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start)
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .widthIn(max = 60.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp)),
                model = ImageRequest.Builder(context)
                    .data(articleImageUrl)
                    .error(R.drawable.ic_launcher_foreground)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true)
                    .build(),
                loading = {
                    CircularProgressIndicator(modifier = Modifier.requiredSize(48.dp))
                },
                contentDescription = "Article Image"
            )

            Column {
                savedAt?.let { savedTime ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Saved at: ${savedTime.dateFormatter()}",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Box {
                            IconButton(
                                onClick = { expanded = true }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.MoreVert,
                                    contentDescription = "Menu"
                                )
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }) {
                                DropdownMenuItem(
                                    text = { Text("Share") },
                                    onClick = {
                                        articleUrl?.let { context.shareArticle(it) }
                                        expanded = false
                                    },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Outlined.Share,
                                            contentDescription = null
                                        )
                                    }
                                )
                                HorizontalDivider()
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            "Delete",
                                            color = MaterialTheme.colorScheme.error
                                        )
                                    },
                                    onClick = {
                                        onDelete()
                                        expanded = false
                                    },
                                    leadingIcon = {
                                        Icon(
                                            Icons.Outlined.Delete,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
                Text(
                    text = articleTitle ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = articleDescription ?: "",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Read More",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .clickable { onReadMore() }
                )
            }
        }
    }
}