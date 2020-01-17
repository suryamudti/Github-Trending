package com.surya.githubtrending.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.surya.githubtrending.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by suryamudti on 12/11/2019.
 */
class NetworkConnectionInterceptor(
    context: Context
) : Interceptor{

    /**
     * context attributes will use for the interceptor
     * */
    private val applicationContext = context.applicationContext

    /**
     * this will override intercept function from [Interceptor] class
     * */
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("Make sure you have an active data connection")
        return chain.proceed(chain.request())
    }

    /**
     * this function will check the availability of the internet
     * @return true if internet available, no for otherwise
     * */
    private fun isInternetAvailable():Boolean{
        var result = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT < 23){
            val activeNetworkInfo = connectivityManager?.activeNetworkInfo
            result = activeNetworkInfo != null && activeNetworkInfo.isConnected
        }else{
            connectivityManager?.let {
                it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }
}