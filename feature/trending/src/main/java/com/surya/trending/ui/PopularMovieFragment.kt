package com.surya.trending.ui

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.surya.trending.R
import com.surya.trending.domain.PopularMovieUseCase
import com.surya.trending.factory.PopularMovieFactory
import kotlinx.android.synthetic.main.fragment_popular_movie.*
import com.surya.abstraction.base.BaseFragment
import com.surya.data.entity.Movie
import com.surya.data.repository.movie.MovieRepository
import com.surya.data.repository.movie.MovieRepositoryImpl
import com.surya.data.routes.NetworkServices
import com.surya.network.Network

class PopularMovieFragment : BaseFragment(){

    override fun contentView(): Int = R.layout.fragment_popular_movie

    private lateinit var repository: MovieRepository
    private lateinit var useCase: PopularMovieUseCase
    private lateinit var viewModel: PopularMovieViewModel

    //adapter
    private val movies = mutableListOf<Movie>()
    private val adapter by lazy {
        PopularMovieAdapter(movies)
    }

    override fun initObservable() {

        val networkBuilder = Network.builder().create(NetworkServices::class.java)

        //init repository
        repository = MovieRepositoryImpl(networkBuilder)

        //init useCase
        useCase = PopularMovieUseCase(repository)

        //init viewModel
        viewModel = ViewModelProviders.of(this, PopularMovieFactory(useCase))
            .get(PopularMovieViewModel::class.java)
    }

    override fun initView() {

        rvPopularMovie.layoutManager = GridLayoutManager(context, 2)
        rvPopularMovie.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            movies.addAll(it.resultsIntent)
            adapter.notifyDataSetChanged()
        })

        viewModel.error.observe(viewLifecycleOwner, showError())
    }

    private fun showError() : Observer<String>{
        return Observer {
            Toast.makeText(context,it, Toast.LENGTH_LONG).show()
        }
    }

}