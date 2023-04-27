package com.example.moviesseal.commons.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesseal.login.AuthViewModel
import com.example.moviesseal.login.views.RegisterView
import com.example.moviesseal.movie.MovieView
import com.example.moviesseal.movies.view.MoviesView

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    var userName = ""
    val onClick: (OnClickModel<Destinations>, name: String) -> Unit = { OnClickModel, name ->
        when (OnClickModel) {
            is OnClickModel.Navigation -> {
                userName = name
                navController.navigate(OnClickModel.destination.name)
            }
        }
    }

    NavHost(navController = navController, startDestination = Destinations.LOGIN.name) {
        composable(Destinations.LOGIN.name) {
            authViewModel.logout()
            RegisterView(onClick)
        }
        composable(Destinations.MOVIES.name) {
            MoviesView(onClick, userName = userName)
        }
        composable(Destinations.MOVIE.name) {
            MovieView(onClick, userName = userName)
        }
    }

}

sealed class OnClickModel<out N> {
    class Navigation<N>(val destination: Destinations) : OnClickModel<N>()
}

enum class Destinations(val destination: String) {
    LOGIN("LOGIN"),
    MOVIES("MOVIES"),
    MOVIE("MOVIE"),
    MOVIE_DETAIL("MOVIE_DETAIL"),
    GENRES("GENRES")
}




