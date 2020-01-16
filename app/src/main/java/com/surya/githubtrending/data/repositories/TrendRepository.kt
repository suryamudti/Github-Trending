package com.surya.githubtrending.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.surya.githubtrending.data.local.LocalDatabase
import com.surya.githubtrending.data.local.entities.Trend
import com.surya.githubtrending.data.preferences.PreferenceProvider
import com.surya.githubtrending.data.remote.RemoteApi
import com.surya.githubtrending.data.remote.SafeApiRequest
import com.surya.githubtrending.util.Coroutines
import com.surya.githubtrending.util.EspressoIdlingResource
import com.surya.githubtrending.util.NoInternetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.Duration
import org.threeten.bp.ZonedDateTime

/**
 * Created by suryamudti on 12/11/2019.
 */
class TrendRepository(
    private val remote : RemoteApi,
    private val local : LocalDatabase,
    private val pref : PreferenceProvider
):SafeApiRequest(){

    /**
     * define constant value of Minimum Interval time range
     * */
    private val MINIMUM_INTERVAL = 2

    /**
     * mutable liveData to hold List of Trend data
     * this liveData value could changed if [fetchTrends] is executed
     * */
    private val trends = MutableLiveData<List<Trend>>()

    /**
     * mutable liveData to hold the status of the network state
     * this liveData value could change if [fetchTrends] is executed
     * this will true if network is fine, will false otherwise
     * */
    private val trendFetchError = MutableLiveData<Boolean>()

    init {
        trends.observeForever{
            saveTrends(it)
        }
    }

    /**
     * this function is using for observe the [trends] from viewModel
     * and run [fetchTrends] function.
     * @return liveData from local DB
     * */
    suspend fun getTrends():LiveData<List<Trend>>{
        return withContext(Dispatchers.IO){
            fetchTrends()
            local.getTrendDao().getAllTrends()
        }
    }

    /**
     * this function is contains boolean type of liveData
     * @return [trendFetchError] for layout state purposes
     * */
    fun getTrendFetchError():LiveData<Boolean>{
        return trendFetchError
    }

    /**
     * this function will fetch the data from Remote Api,
     * and set the [trends] and [trendFetchError] LiveData value using postValue
     * if [isFetchNeeded] function is return true.
     * this function only for internal repository purposes.
     * should call this function inside Coroutine or suspend function
     * */
    private suspend fun fetchTrends(){
        EspressoIdlingResource.increment()
        val lastSavedAt = pref.getLastSavedAt()
        if(lastSavedAt == null || isFetchNeeded(ZonedDateTime.parse(lastSavedAt))){
            try {
                val response = apiRequest { remote.getRepositories() }
                trends.postValue(response)
                trendFetchError.postValue(false)
                EspressoIdlingResource.decrement()
            }catch (e: NoInternetException){
                EspressoIdlingResource.decrement()
                trendFetchError.postValue(true)
                e.printStackTrace()
            }
        }
    }

    /**
     * this function will validate the [saveAt]
     * @return true if the range between [saveAt] and now
     * is more than [MINIMUM_INTERVAL]
     * */
    fun isFetchNeeded(saveAt: ZonedDateTime):Boolean{
        return Duration.between(
            saveAt.toLocalDateTime(),
            ZonedDateTime.now()
        ).toHours() > MINIMUM_INTERVAL
    }

    /**
     * this function will save [trends] data to local db
     * and will save current date time to sharedPreference
     * */
    private fun saveTrends(trends : List<Trend>){
        Coroutines.io {
            pref.saveLastSavedAt(ZonedDateTime.now().toString())
            local.getTrendDao().insertAllTrends(trends)
        }
    }

    /**
     * this function will do forceRequest to Api server,
     * and set the [trends] and [trendFetchError] LiveData value using postValue,
     * should call this function inside Coroutine or suspend function,
     * */
    suspend fun forceFetchToApi(){
        EspressoIdlingResource.increment()
        try {
            val response = apiRequest { remote.getRepositories() }
            trends.postValue(response)
            trendFetchError.postValue(false)
            EspressoIdlingResource.decrement()
        }catch (e: NoInternetException){
            trendFetchError.postValue(true)
            e.printStackTrace()
            EspressoIdlingResource.decrement()
        }
    }

    /**
     * this function will sort the [trends] liveData value based on name
     * */
    fun sortByName(){
        trends.postValue(local.getTrendDao().getAllTrendsSortedByName())
    }

    /**
     * this function will sort the [trends] liveData value based on stars count
     * */
    fun sortByStars(){
        trends.postValue(local.getTrendDao().getAllTrendsSortedByStars())
    }
}