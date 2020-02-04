package com.surya.movie_detail.ui

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.surya.movie_detail.R
import com.surya.movie_detail.domain.MovieDetailUseCase
import com.surya.movie_detail.factory.MovieDetailFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*
import com.surya.abstraction.base.BaseActivity
import com.surya.abstraction.util.ext.load
import com.surya.data.entity.Movie
import com.surya.data.repository.movie_detail.MovieDetailRepository
import com.surya.data.repository.movie_detail.MovieDetailRepositoryImpl
import com.surya.data.routes.NetworkServices
import com.surya.network.Network

class MovieDetailActivity : BaseActivity() {

    private lateinit var repository: MovieDetailRepository
    private lateinit var useCase: MovieDetailUseCase
    private lateinit var viewModel: MovieDetailViewModel

    override fun initView() {

        intent?.data?.lastPathSegment?.let {
            viewModel.setMovieId(it)
        }

        viewModel.error.observe(this, Observer {
            Toast.makeText(this,it, Toast.LENGTH_LONG).show()
        })

        viewModel.movie.observe(this, Observer {
            showMovieDetail(it)
        })

    }

    override fun initObservable() {
        val networkBuilder = Network.builder()
            .create(NetworkServices::class.java)

        //init repository
        repository = MovieDetailRepositoryImpl(networkBuilder)

        //init useCase
        useCase = MovieDetailUseCase(repository)

        //init viewmodel
        viewModel = ViewModelProviders.of(this,MovieDetailFactory(useCase))
            .get(MovieDetailViewModel::class.java)
    }

    override fun contentView(): Int  = R.layout.activity_movie_detail

    private fun showMovieDetail(movie: Movie){
        imgBanner.load(movie.bannerUrl())
        imgPoster.load(movie.posterUrl())

        txtYear.text = movie.releaseDate
        txtMovieName.text = movie.title
        txtContent.text = movie.overview
        txtRating.text = movie.voteAverage.toString()
        txtVote.text = movie.voteCount.toString()
    }
}