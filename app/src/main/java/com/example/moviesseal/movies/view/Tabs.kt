package com.example.moviesseal.movies.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesseal.R
import com.example.moviesseal.movies.MoviesViewModel
import com.example.moviesseal.movies.models.Movie

@Composable
fun Tabs(moviesViewModel: MoviesViewModel, onClick: (Movie) -> Unit) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("En cines", "Lo último", "Top")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = colorResource(id = R.color.secondary_light)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            fontSize = if (tabIndex == index) 20.sp else 16.sp
                        )
                    },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    selectedContentColor = colorResource(id = R.color.foruth_hard),
                    unselectedContentColor = colorResource(id = R.color.white),
                )
            }
        }
        when (tabIndex) {
            0 -> NowMovies(moviesViewModel = moviesViewModel, onClick)
            1 -> LastMovie(moviesViewModel = moviesViewModel, onClick)
            2 -> TopRatedMovies(moviesViewModel = moviesViewModel, onClick)
        }
    }
}