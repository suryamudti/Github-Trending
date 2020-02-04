package com.surya.movie_detail.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surya.movie_detail.domain.MovieDetailUseCase
import com.surya.movie_detail.ui.MovieDetailViewModel

class MovieDetailFactory(
    private val useCase : MovieDetailUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(useCase) as T
    }
}