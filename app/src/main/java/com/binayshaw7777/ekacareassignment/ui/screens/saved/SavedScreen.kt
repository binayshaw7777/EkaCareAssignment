package com.binayshaw7777.ekacareassignment.ui.screens.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SavedScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).then(modifier)
    ) {
        Text("Hi Saved Screen")
    }
}