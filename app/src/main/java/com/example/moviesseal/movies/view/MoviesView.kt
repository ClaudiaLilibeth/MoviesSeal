package com.example.moviesseal.movies.view

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesseal.R
import com.example.moviesseal.login.views.MainActivity
import com.example.moviesseal.login.utils.CONSTANTS
import com.example.moviesseal.movies.MoviesViewModel
import com.example.moviesseal.movies.models.Movie

@Composable
fun MoviesView (
    userName: String,
    moviesViewModel: MoviesViewModel
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
                Icon(modifier = Modifier.padding(top = 16.dp),
                    painter = painterResource(id = R.drawable.user),
                    tint = colorResource(id = R.color.foruth_black),
                    contentDescription = "icon")

                //USERNAME
                Text(text = userName, modifier = Modifier
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
                            val intent = Intent(context, MainActivity::class.java).apply {
                                putExtra(CONSTANTS.LOG_OUT, CONSTANTS.LOG_OUT)
                            }
                        },
                    text = "Cerrar sesión",
                    color = colorResource(id = R.color.black)
                )
            }
        })
         {  padding ->
            Box(modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())) {
            Tabs(moviesViewModel = moviesViewModel)
            }
        }//scaffold
}//view

@Composable
fun Tabs(moviesViewModel: MoviesViewModel) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("En cines", "Lo último", "Top")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex,
            backgroundColor = colorResource(id = R.color.secondary_light)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(text = title,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    fontSize = if(tabIndex == index) 20.sp else 16.sp)},
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    selectedContentColor = colorResource(id = R.color.foruth_hard),
                    unselectedContentColor = colorResource(id = R.color.white),
                )
            }
        }
        when (tabIndex) {
            0 -> NowMovies(moviesViewModel = moviesViewModel)
            1 -> LastMovie(moviesViewModel = moviesViewModel )
            2 -> TopRatedMovies(moviesViewModel = moviesViewModel)
        }
    }
}

@Composable
@UiComposable
fun LastMovie(moviesViewModel: MoviesViewModel) {
    val moviesLast = moviesViewModel.moviesLast.collectAsState()
    val offset = Offset(5.0f, 10.0f)

    Text(text = "LO ÚLTIMO",
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
        MovieItem(movieItem = moviesLast.value)
    }
}

@Composable
@UiComposable
fun TopRatedMovies (moviesViewModel: MoviesViewModel){
    val moviesTopRated = moviesViewModel.moviesTopRated.collectAsState()
    val offset = Offset(5.0f, 10.0f)

    Text(text = "LO MÁS VOTADO",
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
            MovieItem(movieItem = it)
        }
    }
}

@Composable
@UiComposable
fun NowMovies (moviesViewModel: MoviesViewModel){
    val moviesNow = moviesViewModel.moviesNowPlaying.collectAsState()
    val offset = Offset(5.0f, 10.0f)

    Text(text = "EN CINES AHORA",
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
            MovieItem(movieItem = it)
        }
    }
}

@Composable
fun MovieItem(movieItem: Movie){
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = "TÍTULO: " + movieItem.title, modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .weight(1f))
        Text(text ="RESUMEN: " + movieItem.overview, modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .weight(1f))
    }
}



