package com.surya.data.repository.movie

import com.surya.data.entity.Movies
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovie(): Response<Movies>
}