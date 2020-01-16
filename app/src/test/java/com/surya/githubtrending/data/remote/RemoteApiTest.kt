package com.surya.githubtrending.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.surya.githubtrending.BuildConfig
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by suryamudti on 12/11/2019.
 */

@RunWith(JUnit4::class)
class RemoteApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var remoteApi: RemoteApi

    @Rule @JvmField var instantExecutor = InstantTaskExecutorRule()

    @Before fun setup(){

        // initialize for mocking webserver
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // initialize for remoteApi
        remoteApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url(BuildConfig.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RemoteApi::class.java)

    }

    @After fun tearDown(){
        // shut down the mock server
        mockWebServer.shutdown()
    }

    @Test
    fun `test response from API isSuccessFull should return true`(){
        //given
        val expectValue = true

        runBlocking {

            //when
            val trendingResponse = remoteApi.getRepositories()

            //then assert
            assert(trendingResponse.isSuccessful == expectValue)
        }
    }

    @Test fun `test response body from API should not null`(){

        runBlocking {
            //when
            val trendingResponse = remoteApi.getRepositories().body()

            //then assert
            assertNotNull(trendingResponse)
        }
    }

}