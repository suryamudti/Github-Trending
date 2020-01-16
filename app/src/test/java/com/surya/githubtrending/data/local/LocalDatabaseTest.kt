package com.surya.githubtrending.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.surya.githubtrending.data.local.entities.Trend
import junit.framework.Assert.assertEquals
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by suryamudti on 15/11/2019.
 */

@RunWith(RobolectricTestRunner::class)
class LocalDatabaseTest{

    private lateinit var dao : TrendDao

    @Rule
    @JvmField var instantExecutor = InstantTaskExecutorRule()

    @Before fun setup(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        LocalDatabase.TEST_MODE = true
        dao = LocalDatabase.invoke(context).getTrendDao()
    }

    @After fun tearDown(){}

    @Test fun `make sure the result is sorted by name or sorted by stars count`(){
        //given
        val randomList = listOf<Trend>(
            Trend("bill gates","avatar",2000,"description",
                1000,"language","color","bigquery",2000,"url"),
            Trend("surya","avatar",800,"description",
                1000,"language","color","kubernetes",800,"url"),
            Trend("author","avatar",1000,"description",
                1000,"language","color","alamofire",1000,"url")
        )

        val expectedValueSortedByName = listOf<Trend>(
            Trend("author","avatar",1000,"description",
                1000,"language","color","alamofire",1000,"url"),
            Trend("bill gates","avatar",2000,"description",
                1000,"language","color","bigquery",2000,"url"),
            Trend("surya","avatar",800,"description",
                1000,"language","color","kubernetes",800,"url")
        )

        val expectedValueSortedByStarsCount = listOf<Trend>(
            Trend("bill gates","avatar",2000,"description",
                1000,"language","color","bigquery",2000,"url"),
            Trend("author","avatar",1000,"description",
                1000,"language","color","alamofire",1000,"url"),
            Trend("surya","avatar",800,"description",
                1000,"language","color","kubernetes",800,"url")
        )


        //when
        dao.insertAllTrends(randomList)
        val actualValueByName = dao.getAllTrendsSortedByName()
        val actualValueByStars = dao.getAllTrendsSortedByStars()


        //then assert
        assertEquals(expectedValueSortedByName,actualValueByName)
        assertEquals(expectedValueSortedByStarsCount,actualValueByStars)
    }

}