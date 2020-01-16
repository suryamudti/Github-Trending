package com.surya.githubtrending.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.surya.githubtrending.data.local.entities.Trend

/**
 * Created by suryamudti on 13/11/2019.
 */
@Database(entities = [Trend::class],version = 1,exportSchema = false)
abstract class LocalDatabase : RoomDatabase(){

    abstract fun getTrendDao() : TrendDao

    companion object{

        var TEST_MODE = false

        @Volatile
        private var instance: LocalDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) : LocalDatabase{

            return when(TEST_MODE){
                true -> Room.inMemoryDatabaseBuilder(context,LocalDatabase::class.java)
                                    .allowMainThreadQueries().build()
                false -> Room.databaseBuilder(context.applicationContext,
                            LocalDatabase::class.java,
                            "trend.db"
                        ).build()

            }
        }

    }
}