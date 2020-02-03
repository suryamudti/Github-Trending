package com.surya.data.repository.movie

import com.surya.data.entity.Movie
import com.surya.data.entity.Movies
import com.surya.data.routes.NetworkServices
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

class MovieRepositoryTest {

    private var services = mock(NetworkServices::class.java)
    private lateinit var repository: MovieRepository

    private val movies = listOf(
        Movie(
            "id",
            "movieId",
            "title",
            "posterPath",
            "overview",
            "backdrop",
            0,
            0f,
            "relateDate"
        )
    )

    @Before fun setUp() {
        repository = MovieRepositoryImpl(services)
    }

    @Test fun `should get popular movie success`() = runBlocking {
        `when`(services.getPopularMovie()).thenReturn(
            Response.success(Movies(movies))
        )
        val repo = repository.getPopularMovie()

        assertEquals(repo.body(), Movies(movies))
    }

    @Test fun `should get null and error`() = runBlocking {
        `when`(services.getPopularMovie()).thenReturn(
            Response.error(401, ResponseBody.create(MediaType.parse("application/json"), ""))
        )
        val repo = repository.getPopularMovie()

        assertEquals(repo.body(), null)
    }

}