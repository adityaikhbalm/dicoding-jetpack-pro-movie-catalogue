package com.dicoding.bajp.ui.main.favorite.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.bajp.R
import com.dicoding.bajp.di.ViewModelFactory
import com.dicoding.bajp.ui.detail.DetailActivity
import com.dicoding.bajp.ui.main.favorite.FavoritePagedAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteTvFragment : Fragment() {

    private lateinit var viewModelFactory: ViewModelFactory
    private val favoriteViewModel by viewModels<FavoriteTvViewModel> {
        viewModelFactory
    }
    private lateinit var favoriteAdapter: FavoritePagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { viewModelFactory = ViewModelFactory.invoke(it) }
        if (savedInstanceState == null) favoriteViewModel.favoriteTv()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setRecyclerView()
    }

    private fun setAdapter() {
        favoriteAdapter = FavoritePagedAdapter {
            startActivity(
                Intent(context, DetailActivity::class.java)
                    .putExtra(DetailActivity.MOVIE_DATA, it)
            )
        }
    }

    private fun setRecyclerView() {
        with(favorite_recycler) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
        initSwipeToDelete()
    }

    private fun fetchData() =
        favoriteViewModel.fetchFavoriteTv.observe(this, Observer(favoriteAdapter::submitList))

    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as FavoritePagedAdapter.Holder).item?.let {
                    favoriteViewModel.deleteFavorite(it)
                }
            }
        }).attachToRecyclerView(favorite_recycler)
    }
}