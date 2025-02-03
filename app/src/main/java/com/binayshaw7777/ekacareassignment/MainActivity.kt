package com.binayshaw7777.ekacareassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.binayshaw7777.ekacareassignment.ui.components.ExitBackHandler
import com.binayshaw7777.ekacareassignment.ui.navigation.Navigation
import com.binayshaw7777.ekacareassignment.ui.theme.EkaCareAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EkaCareAssignmentTheme {
                ExitBackHandler()
                Navigation()
            }
        }
    }
}