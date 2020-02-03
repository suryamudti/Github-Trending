package com.surya.data.repository.movie_detail

import retrofit2.Response
import com.surya.data.entity.Movie

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId:String): Response<Movie>

}