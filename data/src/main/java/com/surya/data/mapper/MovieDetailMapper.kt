package com.surya.data.mapper

import com.surya.data.entity.Movie
import com.surya.data.entity.MovieDetail

object MovieDetailMapper {

    fun mapFromMovie(movie: Movie): MovieDetail {
        return MovieDetail(
            movie.id,
            movie.movieId,
            movie.title,
            movie.posterUrl(),
            movie.overview,
            movie.bannerUrl(),
            movie.voteCount,
            movie.voteAverage,
            movie.releaseDate
        )
    }

}