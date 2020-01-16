package com.surya.githubtrending.di

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.surya.githubtrending.data.local.LocalDatabase
import com.surya.githubtrending.data.preferences.PreferenceProvider
import com.surya.githubtrending.data.remote.NetworkConnectionInterceptor
import com.surya.githubtrending.data.remote.RemoteApi
import com.surya.githubtrending.data.repositories.TrendRepository
import com.surya.githubtrending.ui.TrendViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by suryamudti on 12/11/2019.
 */

class GithubTrendingApplication : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

    override val kodein by Kodein.lazy {
        import(androidXModule(this@GithubTrendingApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { RemoteApi(instance()) }
        bind() from singleton { LocalDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { TrendRepository(instance(), instance(), instance())}

        bind() from provider { TrendViewModelFactory(instance()) }

    }

}