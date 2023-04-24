package com.example.moviesseal.movies.view

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.moviesseal.login.views.MainActivity
import com.example.moviesseal.login.utils.CONSTANTS
import com.example.moviesseal.movies.MoviesViewModel
import com.example.moviesseal.movies.models.Movie

@Composable
fun MoviesView (
    userName: String,
    moviesViewModel: MoviesViewModel
){
    val context = LocalContext.current
    var trash = rememberSaveable { mutableStateOf(0) }
    val moviesLast = moviesViewModel.moviesLast.collectAsState()
    val moviesTopRated = moviesViewModel.moviesTopRated.collectAsState()
    val moviesNow = moviesViewModel.moviesNowPlaying.collectAsState()


    Box(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())){
            Text(text = userName, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
            Button(onClick = {
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra(CONSTANTS.LOG_OUT, CONSTANTS.LOG_OUT)
                }
                context.startActivity(intent)
            }) {
                Text(text = "Cerrar sesión")
            }

            Text(text = "LAST MOVIES", modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))


            if (moviesLast.value == null) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            } else {
                movieItem(movieItem = moviesLast.value)
            }

            Text(text = "TOP RATED", modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))

            if (moviesTopRated.value.isNullOrEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            } else {
                moviesTopRated.value.forEach {
                    movieItem(movieItem = it)
                }
            }

            Text(text = "MOVIES NOW PLAYING", modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))

            if (moviesNow.value.isNullOrEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            } else {
                moviesNow.value.forEach {
                    movieItem(movieItem = it)
                }
            }

        }
    }



}

@Composable
fun movieItem(movieItem: Movie){
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = "TÍTULO: " + movieItem.title, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
        Text(text ="RESUMEN: " + movieItem.overview, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
    }
}
