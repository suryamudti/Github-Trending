package com.surya.githubtrending.ui

import androidx.lifecycle.ViewModel
import com.surya.githubtrending.data.repositories.TrendRepository
import com.surya.githubtrending.util.Coroutines
import com.surya.githubtrending.util.lazyDeferred

/**
 * Created by suryamudti on 13/11/2019.
 */
class TrendViewModel(
    private val repository: TrendRepository
) : ViewModel(){

    /**
     * this variable will return liveData from repository
     * this contains the list of trends
     * */
    val trends by lazyDeferred{
        repository.getTrends()
    }

    /**
     * this variable will return live data from repository,
     * this contains the state of the network
     * */
    val trendFetchError by lazy {
        repository.getTrendFetchError()
    }

    /**
     * this function will sort the trend list by stars
     * */
    fun sortByStars(){
        Coroutines.io { repository.sortByStars() }
    }

    /**
     * this function will sort the trend list by name
     * */
    fun sortByName(){
        Coroutines.io { repository.sortByName() }
    }

    /**
     * this function will do request to Api without validation
     * */
    fun forceFetch(){
        Coroutines.main { repository.forceFetchToApi() }
    }
}