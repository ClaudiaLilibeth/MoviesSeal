package com.example.moviesseal.movies.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import com.example.moviesseal.movies.models.Movie
import com.example.moviesseal.movies.view.ui.theme.MovieItem


@Composable
@UiComposable
fun LastMovie(moviesViewModel: MoviesViewModel, onClick: (Movie) -> Unit) {
    val moviesLast = moviesViewModel.moviesLast.collectAsState()
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = "LO ÚLTIMO",
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
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

    if (moviesLast.value == null) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
    } else {
        moviesViewModel.insertMovieAndGenre(moviesLast.value)
        MovieItem(movieItem = moviesLast.value, onClick)
    }
}
