package com.example.moviesseal.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesseal.R
import com.example.moviesseal.commons.navigation.Destinations
import com.example.moviesseal.commons.navigation.OnClickModel
import com.example.moviesseal.movies.MoviesViewModel

@Composable
fun MovieView(
    onClick: (OnClickModel<Destinations>, name: String) -> Unit,
    moviesViewModel: MoviesViewModel = hiltViewModel(),
    userName: String,
) {
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
        }) {
        Text(
            text = "detalle de pelicula ${moviesViewModel.selectedMovie.value.title}",
            modifier = Modifier.padding(it)
        )
    }
}