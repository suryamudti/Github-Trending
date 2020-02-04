package com.surya.movie_detail.domain

import com.surya.abstraction.util.state.ResultState
import com.surya.data.entity.Movie
import com.surya.data.repository.movie_detail.MovieDetailRepository

class MovieDetailUseCase (
    private val repository: MovieDetailRepository
){
    suspend fun get(movieId : String):ResultState<Movie>{
        val response = repository.getMovieDetail(movieId)
        return if(response.isSuccessful){
            ResultState.Success(response.body()?:Movie())
        }else{
            ResultState.Error(MOVIE_DETAIL_ERROR)
        }
    }

    companion object{
        private const val MOVIE_DETAIL_ERROR = "duh error bro"
    }

}