package com.dicoding.bajp.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.bajp.R
import com.dicoding.bajp.ui.main.favorite.movie.FavoriteMovieFragment
import com.dicoding.bajp.ui.main.favorite.tv.FavoriteTvFragment
import kotlinx.android.synthetic.main.fragment_category_favorite.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        val fragments = listOf(
            FavoriteMovieFragment(),
            FavoriteTvFragment()
        )
        val title = listOf(
            "Movies",
            "Tv Shows"
        )
        category_favorite_viewpager.adapter =
            FavoriteAdapter(title, fragments, childFragmentManager)
    }

    private fun setupTabLayout() = tab_favorites.setupWithViewPager(category_favorite_viewpager)
}