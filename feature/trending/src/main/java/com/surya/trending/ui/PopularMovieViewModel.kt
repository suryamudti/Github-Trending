package com.surya.trending.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surya.trending.domain.PopularMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.surya.abstraction.util.state.ResultState
import com.surya.data.entity.Movies

interface PopularMovieContract{
    fun getPopularMovie()
}

class PopularMovieViewModel(
    private val useCase : PopularMovieUseCase
) : ViewModel(), PopularMovieContract{

    private val _movies = MutableLiveData<Movies>()
    val movies : LiveData<Movies> get() = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init{
        getPopularMovie()
    }

    override fun getPopularMovie(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.get()

            withContext(Dispatchers.Main){
                when(response){
                    is ResultState.Success -> {
                        _movies.value = response.data
                    }
                    is ResultState.Error -> {
                        _error.value = response.error
                    }
                }
            }
        }
    }
}