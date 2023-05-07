package com.example.moviesseal.commons.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesseal.login.RegisterView
import com.example.moviesseal.movie.MovieView
import com.example.moviesseal.movies.MoviesViewModel
import com.example.moviesseal.remote.movies.models.Movie
import com.example.moviesseal.movies.view.MoviesView

@Composable
fun NavigationGraph(
    viewModel: MoviesViewModel,
    navController: NavHostController,
) {
    var userName = ""
    var movieSelected = Movie()

    val onClick: (OnClickModel<Destinations>, name: String) -> Unit = { OnClickModel, name ->
        when (OnClickModel) {
            is OnClickModel.Navigation -> {
                userName = name
                navController.navigate(OnClickModel.destination.name)
            }
        }
    }

    val onClickMovie: (OnClickModel<Destinations>, name: String, movie: Movie) -> Unit =
        { OnClickModel, name, movie ->
            when (OnClickModel) {
                is OnClickModel.Navigation -> {
                    userName = name
                    movieSelected = movie
                    viewModel.getGenfromMov(movie.id)
                    navController.navigate(OnClickModel.destination.name)
                }
            }
        }

    NavHost(navController = navController, startDestination = Destinations.LOGIN.name) {
        composable(Destinations.LOGIN.name) {
            RegisterView(onClick)
        }
        composable(Destinations.MOVIES.name) {
            MoviesView(
                onClickMovies = onClickMovie,
                onClick = onClick,
                userName = userName,
                viewModel
            )
        }
        composable(Destinations.MOVIE.name) {
            MovieView(
                onClick,
                userName = userName,
                movie = movieSelected,
                moviesViewModel = viewModel
            )
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
}




