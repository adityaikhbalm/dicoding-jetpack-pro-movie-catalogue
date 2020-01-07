package com.dicoding.bajp.ui.main.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.bajp.R
import com.dicoding.bajp.data.network.Status
import com.dicoding.bajp.di.ViewModelFactory
import com.dicoding.bajp.ui.detail.DetailActivity
import com.dicoding.bajp.utlis.ItemOffsetDecoration
import com.dicoding.bajp.utlis.Simplify.calculateNoOfColumns
import com.dicoding.bajp.utlis.Simplify.hideIt
import com.dicoding.bajp.utlis.Simplify.showIt
import com.dicoding.bajp.utlis.Simplify.toastShow
import kotlinx.android.synthetic.main.fragment_movie_tv.*

class MovieFragment : Fragment() {

    private lateinit var viewModelFactory: ViewModelFactory
    private val movieViewModel by viewModels<MovieViewModel> { viewModelFactory }
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { viewModelFactory = ViewModelFactory.invoke(it) }
        if (savedInstanceState == null) movieViewModel.allMovies()
        setViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setRecyclerView()
    }

    private fun setAdapter() {
        movieAdapter = MovieAdapter {
            startActivity(
                Intent(context, DetailActivity::class.java)
                    .putExtra(DetailActivity.MOVIE_DATA, it)
            )
        }
    }

    private fun setRecyclerView() {
        with(movie_tv_recycler) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, calculateNoOfColumns(130f))
            addItemDecoration(ItemOffsetDecoration())
            adapter = movieAdapter
        }
    }

    private fun setViewModel() {
        movieViewModel.fetchMovie.observe(this, Observer { movie ->
            when (movie.status) {
                Status.SUCCESS -> {
                    showLoading(false)
                    val data = movie.data?.results
                    data?.let { movieAdapter.submitList(data) }
                }
                Status.ERROR -> {
                    showError(movie.message)
                    showLoading(true)
                }
                Status.LOADING -> {
                    showLoading(true)
                }
            }
        })
    }

    private fun showError(message: Int?) = message?.let { context?.toastShow(message) }

    private fun showLoading(loading: Boolean) {
        if (loading) movie_tv_loading.showIt()
        else movie_tv_loading.hideIt()
    }
}