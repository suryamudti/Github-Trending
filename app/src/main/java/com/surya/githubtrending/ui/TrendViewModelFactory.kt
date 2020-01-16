package com.surya.githubtrending.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surya.githubtrending.data.repositories.TrendRepository

/**
 * Created by suryamudti on 13/11/2019.
 */

class TrendViewModelFactory (val repository: TrendRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TrendViewModel::class.java))
            return TrendViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}