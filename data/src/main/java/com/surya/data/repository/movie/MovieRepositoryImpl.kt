package com.surya.data.repository.movie

import retrofit2.Response
import com.surya.data.entity.Movies
import com.surya.data.routes.NetworkServices

class MovieRepositoryImpl constructor(
    private val service: NetworkServices
): MovieRepository {

    override suspend fun getPopularMovie(): Response<Movies> {
        return service.getPopularMovie()
    }

}