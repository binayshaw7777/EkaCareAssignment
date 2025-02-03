package com.binayshaw7777.ekacareassignment.ui.screens.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.binayshaw7777.ekacareassignment.ui.components.shimmerBrush

@Composable
fun HomeScreenShimmerState(modifier: Modifier = Modifier) {
    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        items(10) {
            ArticleCardShimmer()
        }
    }
}

@Composable
fun ArticleCardShimmer(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .then(modifier),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start)
        ) {
            Spacer(
                modifier = Modifier
                    .sizeIn(maxWidth = 60.dp, maxHeight = 60.dp)
                    .background(shimmerBrush())
            )

            Column {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .background(shimmerBrush())
                )
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .background(shimmerBrush())
                )
                Spacer(modifier = Modifier.height(12.dp))

                Spacer(
                    modifier = Modifier
                        .width(50.dp)
                        .height(12.dp)
                        .background(shimmerBrush())
                )
            }
        }
    }
}