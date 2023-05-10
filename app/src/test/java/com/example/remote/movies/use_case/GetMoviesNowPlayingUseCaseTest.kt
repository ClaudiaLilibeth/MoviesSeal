package com.example.remote.movies.use_case

import com.example.remote.movies.api.MoviesDBApi
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetMoviesNowPlayingUseCaseTest {

    private val mockMoviesDBApi = mockk<MoviesDBApi>(relaxed = true)

    @Before
    fun setup(){
        MockKAnnotations.init(this )
       // coEvery { mockMoviesDBApi.getMoviesNowPlaying(any()) just runs }
    }
}