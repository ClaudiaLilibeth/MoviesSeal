package com.example.moviesseal.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesseal.R
import com.example.moviesseal.commons.navigation.Destinations
import com.example.moviesseal.commons.navigation.OnClickModel
import com.example.moviesseal.movies.MoviesViewModel
import com.example.moviesseal.movies.models.Movie
import com.example.moviesseal.movies.view.ui.theme.MovieItem

@Composable
fun MovieView(
    onClick: (OnClickModel<Destinations>, name: String) -> Unit,
    movie: Movie,
    userName: String,
    moviesViewModel: MoviesViewModel,
) {
    val genres = moviesViewModel.genresDetaill.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.secondary_midlight))
            ) {

                Spacer(modifier = Modifier.padding(start = 16.dp))

                Icon(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable {
                            onClick.invoke(
                                OnClickModel.Navigation(Destinations.MOVIES),
                                userName
                            )
                        },
                    painter = painterResource(id = R.drawable.back),
                    tint = colorResource(id = R.color.foruth_black),
                    contentDescription = "icon"
                )

                Spacer(modifier = Modifier.padding(start = 16.dp))

                Icon(
                    modifier = Modifier.padding(top = 16.dp),
                    painter = painterResource(id = R.drawable.user),
                    tint = colorResource(id = R.color.foruth_black),
                    contentDescription = "icon"
                )

                //USERNAME
                Text(
                    text = userName, modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.foruth_black)
                )//txt
            }
        }) {
        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())

        ) {
            Spacer(modifier = Modifier.padding(top = 24.dp))
            MovieItem(movieItem = movie, onClick = {})
            //DESCRIPTION
            Text(
                modifier = Modifier.padding(24.dp),
                text = movie.overview,
                fontWeight = FontWeight.ExtraLight,
                style = TextStyle(
                    fontSize = 20.sp,
                ),
                color = colorResource(id = R.color.black)
            )


            Text(
                text = "GÉNERO: ", Modifier.padding(start = 24.dp, bottom = 8.dp),
                fontWeight = FontWeight.Light,
                style = TextStyle(
                    fontSize = 20.sp,
                    shadow = Shadow(
                        color = colorResource(id = R.color.teal_700),
                        blurRadius = 3f
                    )
                ),
                color = colorResource(id = R.color.fourth_soft),
            )

            if (genres.value.isNotEmpty()) {
                genres.value.forEach { string ->
                    Text(
                        text = string, modifier = Modifier.padding(start = 24.dp, bottom = 8.dp),
                        fontWeight = FontWeight.ExtraLight,
                        style = TextStyle(
                            fontSize = 16.sp,
                        ),
                        color = colorResource(id = R.color.black)
                    )
                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.padding(top = 24.dp))
        }
    }
}