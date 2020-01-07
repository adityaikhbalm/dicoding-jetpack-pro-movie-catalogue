package com.dicoding.bajp.ui.main.tv

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
import com.dicoding.bajp.utlis.Simplify
import com.dicoding.bajp.utlis.Simplify.hideIt
import com.dicoding.bajp.utlis.Simplify.showIt
import com.dicoding.bajp.utlis.Simplify.toastShow
import kotlinx.android.synthetic.main.fragment_movie_tv.*

class TvFragment : Fragment() {

    private lateinit var viewModelFactory: ViewModelFactory
    private val tvViewModel by viewModels<TvViewModel> { viewModelFactory }
    private lateinit var tvAdapter: TvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { viewModelFactory = ViewModelFactory.invoke(it) }
        if (savedInstanceState == null) tvViewModel.allTvShows()
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
        tvAdapter = TvAdapter {
            startActivity(
                Intent(context, DetailActivity::class.java)
                    .putExtra(DetailActivity.MOVIE_DATA, it)
            )
        }
    }

    private fun setRecyclerView() {
        with(movie_tv_recycler) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, Simplify.calculateNoOfColumns(130f))
            addItemDecoration(ItemOffsetDecoration())
            adapter = tvAdapter
        }
    }

    private fun setViewModel() {
        tvViewModel.fetchTv.observe(this, Observer { tv ->
            when (tv.status) {
                Status.SUCCESS -> {
                    showLoading(false)
                    val data = tv.data?.results
                    data?.let { tvAdapter.submitList(data) }
                }
                Status.ERROR -> {
                    showError(tv.message)
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