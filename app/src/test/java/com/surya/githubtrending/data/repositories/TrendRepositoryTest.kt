package com.surya.githubtrending.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.threeten.bp.Duration
import org.threeten.bp.ZonedDateTime

/**
 * Created by suryamudti on 13/11/2019.
 */

@RunWith(JUnit4::class)
class TrendRepositoryTest{

    @Rule @JvmField val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(TrendRepository::class.java)

    @Before fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @After fun tearDown(){}

    @Test fun `testing isFetchedNeeded() should return false`(){

        //given
        val expectedValue = false
        val dateTime = ZonedDateTime.now().plusHours(3)

        //when
        val actualValue = repository.isFetchNeeded(dateTime)

        //then assert
        assert(expectedValue==actualValue)

    }

    @Test fun `testing isFetchedNeeded() difference time should return 2`(){
        //given
        val expectedValue = 2L
        val dateTime = ZonedDateTime.now().minusHours(2)

        //when
        val actualValue = Duration.between(
            dateTime.toLocalDateTime(),
            ZonedDateTime.now()
        ).toHours()

        //then assert
        assert(expectedValue==actualValue)
    }


}