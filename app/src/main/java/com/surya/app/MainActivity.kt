package com.surya.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.surya.trending.ui.PopularMovieFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflateFragment()
    }

    private fun inflateFragment(){

        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.container, PopularMovieFragment())
        transaction.commit()
    }

}
