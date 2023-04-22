package com.example.moviesseal.login.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.moviesseal.login.utils.CONSTANTS
import com.example.moviesseal.login.AuthViewModel
import com.example.moviesseal.login.view.RegisterView
import com.example.moviesseal.movies.view.MoviesActivity
import com.example.moviesseal.ui.theme.MoviesSealTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra(CONSTANTS.LOG_OUT)
        if(name == CONSTANTS.LOG_OUT){
            viewModel.logout()
        }

        if (viewModel.currentUser != null) {
            val intent = Intent(this, MoviesActivity::class.java).apply {
                putExtra(CONSTANTS.NAME, viewModel.currentUser?.displayName)
            }
            startActivity(intent)
        } else {
            setContent {
                MoviesSealTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        RegisterView(
                            viewModel
                        )
                    }
                }
            }
        }
    }
}


