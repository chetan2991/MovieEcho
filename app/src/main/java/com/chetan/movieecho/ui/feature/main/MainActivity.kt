package com.chetan.movieecho.ui.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import com.chetan.movieecho.ui.feature.movies.composables.MoviesScreen
import com.chetan.movieecho.ui.navigation.MovieEchoAppNavigation
import com.chetan.movieecho.ui.theme.MovieEchoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieEchoAppTheme() {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    tonalElevation = 5.dp,
                    shadowElevation = 10.dp
                ) {
                  MovieEchoAppNavigation()
                }
            }
        }
    }
}
