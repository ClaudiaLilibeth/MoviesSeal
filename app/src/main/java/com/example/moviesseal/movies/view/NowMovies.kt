package com.example.moviesseal.movies.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesseal.R
import com.example.moviesseal.movies.MoviesViewModel
import com.example.remote.movies.models.Movie
import com.example.moviesseal.movies.view.ui.theme.MovieItem

@Composable
@UiComposable
fun NowMovies(moviesViewModel: MoviesViewModel, onClick: (com.example.remote.movies.models.Movie) -> Unit) {
    val moviesNow = moviesViewModel.moviesNowPlaying.collectAsState()
    val offset = Offset(5.0f, 10.0f)

    Text(
        text = "EN CINES AHORA",
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 24.dp),
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            fontSize = 32.sp,
            shadow = Shadow(
                color = colorResource(id = R.color.secondary_hard),
                offset = offset,
                blurRadius = 3f
            )
        ),
        color = colorResource(id = R.color.secondary_light),
    )

    if (moviesNow.value.isEmpty()) {
        Spacer(modifier = Modifier.padding(100.dp))
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
    } else {
        moviesNow.value.forEach {
            moviesViewModel.insertMovieAndGenre(it)
            MovieItem(movieItem = it, onClick)
        }
    }
}