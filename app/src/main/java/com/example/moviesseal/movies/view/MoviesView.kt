package com.example.moviesseal.movies.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.moviesseal.R
import com.example.moviesseal.commons.navigation.Destinations
import com.example.moviesseal.commons.navigation.OnClickModel
import com.example.moviesseal.movies.MoviesViewModel
import com.example.remote.movies.models.Movie

@Composable
fun MoviesView(
    onClickMovies: (OnClickModel<Destinations>, name: String, movie: com.example.remote.movies.models.Movie) -> Unit,
    onClick: (OnClickModel<Destinations>, name: String) -> Unit,
    userName: String,
    moviesViewModel: MoviesViewModel,
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
                    modifier = Modifier.padding(top = 12.dp),
                    painter = painterResource(id = R.drawable.user),
                    tint = colorResource(id = R.color.foruth_hard),
                    contentDescription = "icon"
                )

                //USERNAME
                Text(
                    text = userName, modifier = Modifier
                        .padding(12.dp)
                        .weight(1f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.foruth_hard)
                )//txt

                //CERRAR SESIÒN
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            moviesViewModel.logOut()
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
                onClickMovies.invoke(OnClickModel.Navigation(Destinations.MOVIE), userName, movie)
            }
        }
    }//scaffold
}//view


