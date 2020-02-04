package com.surya.trending.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surya.trending.domain.PopularMovieUseCase
import com.surya.trending.ui.PopularMovieViewModel

class PopularMovieFactory(
    private val useCase : PopularMovieUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMovieViewModel(useCase) as T
    }
}