package com.dicoding.bajp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dicoding.bajp.BuildConfig
import com.dicoding.bajp.R
import com.dicoding.bajp.data.local.model.Favorite
import com.dicoding.bajp.data.remote.response.ApiResult
import com.dicoding.bajp.di.ViewModelFactory
import com.dicoding.bajp.utlis.Constant
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_DATA = "data"
    }

    private val data by lazy { intent.getParcelableExtra(MOVIE_DATA) as ApiResult }
    private var favorite: Boolean = false
    private val insert by lazy {
        Favorite(
            data.id,
            data.backdropPath,
            data.originalLanguage,
            data.overview,
            data.posterPath,
            data.releaseDate ?: data.firstAirDate,
            data.title ?: data.name,
            data.voteAverage,
            data.voteCount,
            data.type
        )
    }

    private lateinit var viewModelFactory: ViewModelFactory
    private val detailViewModel by viewModels<DetailViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModelFactory = ViewModelFactory.invoke(applicationContext)
        if (savedInstanceState == null) detailViewModel.fetchFavorite(data.id)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setDetailContent()
    }

    @SuppressLint("DefaultLocale")
    private fun setDetailContent() {
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + Constant.image_size_500 + data.backdropPath)
            .centerCrop()
            .into(detail_poster)
        detail_title.text = data.title ?: data.name
        detail_rating.text = data.voteCount.toString()
        detail_language.text = data.originalLanguage
        detail_release.text = data.releaseDate ?: data.firstAirDate
        detail_overview.text = data.overview ?: getString(R.string.no_translations)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.favorite_menu -> {
                if (favorite) {
                    favorite = false
                    detailViewModel.deleteFavorite(insert)
                } else {
                    favorite = true
                    detailViewModel.insertFavorite(insert)
                }
                changeIconFavorite(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        detailViewModel.isFavorite.observe(this, Observer {
            favorite = it != null
            changeIconFavorite(menu?.getItem(0))
        })
        return true
    }

    private fun changeIconFavorite(item: MenuItem?) {
        if (favorite) item?.setIcon(R.drawable.ic_favorite_fill)
        else item?.setIcon(R.drawable.ic_favorite)
    }
}
