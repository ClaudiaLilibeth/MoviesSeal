package com.example.moviesseal.commons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.moviesseal.commons.navigation.NavigationGraph
import com.example.moviesseal.ui.theme.MoviesSealTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }
}

fun MainActivity.initView() {
    setContent {
        val navController = rememberNavController()
        navController.currentDestination
        MoviesSealTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                NavigationGraph(
                    navController = navController
                )
            }
        }
    }
}




