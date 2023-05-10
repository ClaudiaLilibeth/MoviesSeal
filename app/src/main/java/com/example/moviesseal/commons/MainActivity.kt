package com.example.moviesseal.commons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.moviesseal.commons.navigation.NavigationGraph
import com.example.moviesseal.movies.MoviesViewModel
import com.example.moviesseal.ui.theme.MoviesSealTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView(viewModel)
    }
}

fun MainActivity.initView(viewModel: MoviesViewModel) {
    setContent {
        val navController = rememberNavController()
        navController.currentDestination
        MoviesSealTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                NavigationGraph(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}






