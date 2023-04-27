package com.example.moviesseal.movies.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesseal.R
import com.example.moviesseal.commons.navigation.Destinations
import com.example.moviesseal.commons.navigation.OnClickModel
import com.example.moviesseal.movies.MoviesViewModel
import com.example.moviesseal.movies.models.Movie

@Composable
fun MoviesView(
    onClick: (OnClickModel<Destinations>, name: String) -> Unit,
    userName: String,
    moviesViewModel: MoviesViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

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

                //CERRAR SESIÒN
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            onClick.invoke(
                                OnClickModel.Navigation(Destinations.LOGIN),
                                userName
                            )
                        },
                    text = "Cerrar sesión",
                    color = colorResource(id = R.color.black)
                )
            }
        })
    { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Tabs(moviesViewModel = moviesViewModel) { movie ->
                moviesViewModel.setSelectedMovie(movie)
                onClick.invoke(OnClickModel.Navigation(Destinations.MOVIE), userName)
            }
        }
    }//scaffold
}//view

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
        MovieItem(movieItem = moviesLast.value, onClick)
    }
}

@Composable
@UiComposable
fun TopRatedMovies(moviesViewModel: MoviesViewModel, onClick: (Movie) -> Unit) {
    val moviesTopRated = moviesViewModel.moviesTopRated.collectAsState()
    val offset = Offset(5.0f, 10.0f)

    Text(
        text = "LO MÁS VOTADO",
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

    if (moviesTopRated.value.isEmpty()) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
    } else {
        moviesTopRated.value.forEach {
            MovieItem(movieItem = it, onClick)
        }
    }
}

@Composable
@UiComposable
fun NowMovies(moviesViewModel: MoviesViewModel, onClick: (Movie) -> Unit) {
    val moviesNow = moviesViewModel.moviesNowPlaying.collectAsState()
    val offset = Offset(5.0f, 10.0f)

    Text(
        text = "EN CINES AHORA",
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

    if (moviesNow.value.isEmpty()) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
    } else {
        moviesNow.value.forEach {
            MovieItem(movieItem = it, onClick)
        }
    }
}

@Composable
fun MovieItem(movieItem: Movie, onClick: (Movie) -> Unit) {
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = "TÍTULO: " + movieItem.title, modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .weight(1f)
            .clickable {
                onClick.invoke(movieItem)
            }
        )
        Text(
            text = "RESUMEN: " + movieItem.overview, modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .weight(1f)
        )
    }
}

