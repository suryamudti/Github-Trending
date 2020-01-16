package com.surya.githubtrending.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.surya.githubtrending.R
import com.surya.githubtrending.util.Coroutines
import com.surya.githubtrending.util.hide
import com.surya.githubtrending.util.show
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    /**
     * override the KodeinAware object for injection
     * */
    override val kodein by kodein()

    /**
     * define the factory from injection
     * this will use for our viewModel so we can pass the repository to viewModel constructor
     * */
    private val factory : TrendViewModelFactory by instance()

    /**
     * define the viewModel
     * */
    private lateinit var viewModel : TrendViewModel

    /**
     * define the retryButton
     * this button will use for forceFetch data from Api Remote server
     * */
    private lateinit var retryButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set custom Toolbar
        setSupportActionBar(trToolbar)

        //initialize viewModel
        viewModel = ViewModelProviders.of(this,factory).get(TrendViewModel::class.java)

        //binding and configure the UI layout components
        bindUI()

    }

    /**
     * bind define all the layout and observing the liveData from viewModel
     * */
    private fun bindUI() = Coroutines.main {

        //hiding the error layout
        error_layout.hide()

        //define the include view for the error layout
        defineIncludeView()

        //observing the trends liveData and handle the result list to recyclerView
        viewModel.trends.await().observe(this, Observer {
            shimmer_layout.hide()

            val layoutManager = LinearLayoutManager(this)
            recycler_view_trend.layoutManager = layoutManager
            val adapter = ItemAdapter(layoutManager)
            adapter.setData(it)
            recycler_view_trend.adapter = adapter

        })

        //observing the trendFetchError liveData and handle the result for showing the error layout
        viewModel.trendFetchError.observe(this, Observer {
            if(it){
                error_layout.show()
                shimmer_layout.hide()
                recycler_view_trend.hide()
                swipe_refresh.isRefreshing = false
            }else if(it == false){
                error_layout.hide()
                shimmer_layout.hide()
                recycler_view_trend.show()
                swipe_refresh.isRefreshing = false
            }
        })

        //set the action when user swipe the layout
        swipe_refresh.setOnRefreshListener {
            shimmer_layout.show()
            recycler_view_trend.hide()
            viewModel.forceFetch()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //inflate our menu
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            //sort by name
            R.id.sort_by_names -> viewModel.sortByName()
            //sort by stars
            R.id.sort_by_stars -> viewModel.sortByStars()
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * define the included view from error_layout,
     * and set the action when [retryButton] is clicked
     * */
    private fun defineIncludeView(){
        retryButton = error_layout.findViewById(R.id.no_internet_connection_retry_button) as Button
        retryButton.setOnClickListener {
            error_layout.hide()
            shimmer_layout.show()
            recycler_view_trend.hide()
            viewModel.forceFetch()
        }
    }


}
