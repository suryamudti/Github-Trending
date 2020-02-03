package com.surya.data.repository.movie_detail

import retrofit2.Response
import com.surya.data.entity.Movie
import com.surya.data.routes.NetworkServices

class MovieDetailRepositoryImpl(
    private val services: NetworkServices
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: String): Response<Movie> {
        return services.getMovieDetail(movieId)
    }

}