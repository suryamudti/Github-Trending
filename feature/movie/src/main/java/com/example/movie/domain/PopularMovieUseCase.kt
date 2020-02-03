package com.example.movie.domain

import com.surya.abstraction.util.state.ResultState
import com.surya.data.entity.Movies
import com.surya.data.repository.movie.MovieRepository

class PopularMovieUseCase(
    private val repository: MovieRepository
) {

    suspend fun get(): ResultState<Movies> {
        val popularMovie = repository.getPopularMovie()

        return if(popularMovie.isSuccessful) ResultState.Success(popularMovie.body()!!)
        else ResultState.Error(MOVIE_ERROR)

    }

    companion object {
        private const val MOVIE_ERROR = "Aduh, gagal broo !!! "
    }


}