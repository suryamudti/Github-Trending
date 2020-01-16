package com.surya.githubtrending.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.surya.githubtrending.data.local.entities.Trend
import com.surya.githubtrending.data.repositories.TrendRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by suryamudti on 13/11/2019.
 */
@RunWith(JUnit4::class)
class TrendViewModelTest{

    @Rule @JvmField val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(TrendRepository::class.java)
    private lateinit var viewModel: TrendViewModel

    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TrendViewModel(repository)
    }

    @After fun tearDown() {}

    @Test fun `make sure the trends is not null`(){

        val dummyContent:MutableLiveData<List<Trend>> = MutableLiveData()

        val list = mock(List::class.java)

        dummyContent.value = list as List<Trend>

        runBlocking {
            `when`(repository.getTrends()).thenReturn(dummyContent)
            val observer = mock(Observer::class.java) as Observer<List<Trend>>

            viewModel.trends.await().observeForever(observer)

            verify(observer).onChanged(list)

            assertNotNull(viewModel.trends)

        }
    }

}