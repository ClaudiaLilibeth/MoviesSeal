package com.example.local.testing

import com.example.local.entities.GenreEntity
import com.example.local.entities.GenresAndMoviesCross
import com.example.local.entities.MovieEntity
import com.example.local.repository.GenreRepository
import com.example.local.repository.GenresDao
import com.example.moviesseal.commons.Mock
import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class GenreRepositoryTest{

    private lateinit var sut: GenreRepository
    private val mockGenresDao = mockk<GenresDao>(relaxed = true)

    @Before
    fun `setup`() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sut = GenreRepository(mockGenresDao)
    }

    @Test
    fun `insert genres in dao` () = runTest{  //CREATE 3 GENRES
        //given
        val genreBelico = Mock.createGenreEntityBelico()
        val genreHistorico = Mock.createGenreEntityHistorico()
        val genreSuspenso= Mock.createGenreEntitySuspenso()

        val captureGenreBelico =  slot<GenreEntity>()
        coEvery { mockGenresDao.insertGenre(capture(captureGenreBelico)) } just runs
        val captureGenreHistorico =  slot<GenreEntity>()
        coEvery { mockGenresDao.insertGenre(capture(captureGenreHistorico)) } just runs
        val captureGenreSuspenso =  slot<GenreEntity>()
        coEvery { mockGenresDao.insertGenre(capture(captureGenreSuspenso)) } just runs

        sut.insertGenre(genreBelico)
        sut.insertGenre(genreHistorico)
        sut.insertGenre(genreSuspenso)

        Truth.assertThat(genreBelico).isEqualTo(captureGenreBelico.captured)
        coVerify ( atLeast = 1) {mockGenresDao.insertGenre(genreBelico)}
        Truth.assertThat(genreHistorico).isEqualTo(captureGenreHistorico.captured)
        coVerify ( atLeast = 1) {mockGenresDao.insertGenre(genreHistorico)}
        Truth.assertThat(genreSuspenso).isEqualTo(captureGenreSuspenso.captured)
        coVerify ( atLeast = 1) {mockGenresDao.insertGenre(genreSuspenso)}
    }


    @Test
    fun `insert movies in dao`() = runTest {
        val movies = Mock.createMoviesList()
        val captureMovies = slot<List<MovieEntity>>()
        coEvery { mockGenresDao.insertMovie(capture(captureMovies))} just runs

        sut.insertMovie(movies as ArrayList<MovieEntity>)

        Truth.assertThat(movies).isEqualTo(captureMovies.captured)
        coVerify (atLeast = 1) {mockGenresDao.insertMovie(movies)}
    }

    @Test
    suspend fun `insert genres and movies cross in dao`() = runTest {
        val cross = Mock.createMovieGenreCross()
        val captureCross= slot < List<GenresAndMoviesCross>>()

        coEvery { mockGenresDao.insertGenreToMovie(capture(captureCross))} just runs
        sut.insertMoviesToGenre(cross)

        Truth.assertThat(cross).isEqualTo(captureCross.captured)
        coVerify (atLeast = 1) {mockGenresDao.insertGenreToMovie(cross)}

    }

}