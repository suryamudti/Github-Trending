package com.surya.githubtrending.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surya.githubtrending.data.local.entities.Trend

/**
 * Created by suryamudti on 13/11/2019.
 */
@Dao
interface TrendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTrends(trends : List<Trend>)

    @Query("SELECT * FROM Trend")
    fun getAllTrends() : LiveData<List<Trend>>

    @Query("SELECT * FROM Trend ORDER BY name ASC")
    fun getAllTrendsSortedByName() : List<Trend>

    @Query("SELECT * FROM Trend ORDER BY stars DESC")
    fun getAllTrendsSortedByStars() : List<Trend>

}