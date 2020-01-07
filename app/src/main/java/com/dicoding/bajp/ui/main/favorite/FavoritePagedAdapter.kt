package com.dicoding.bajp.ui.main.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.bajp.BuildConfig
import com.dicoding.bajp.R
import com.dicoding.bajp.data.local.model.Favorite
import com.dicoding.bajp.data.remote.response.ApiResult
import com.dicoding.bajp.utlis.Constant
import com.dicoding.bajp.utlis.Simplify
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoritePagedAdapter(
    private val onClick: (ApiResult) -> Unit
) : PagedListAdapter<Favorite, FavoritePagedAdapter.Holder>(ItemCallback) {

    companion object {
        val ItemCallback = object : DiffUtil.ItemCallback<Favorite>() {
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorite, parent, false)
        )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position) ?: return
        holder.bindTo(item)
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        var item: Favorite? = null

        fun bindTo(favorite: Favorite) {
            item = favorite
            with(itemView) {
                detail_favorite.setOnClickListener {
                    val data = ApiResult(
                        favorite.id,
                        favorite.backdropPath,
                        null,
                        null,
                        favorite.originalLanguage,
                        favorite.overview,
                        favorite.posterPath,
                        favorite.releaseDate,
                        favorite.title,
                        favorite.voteAverage,
                        favorite.voteCount,
                        favorite.type
                    )
                    onClick(data)
                }
                Glide.with(context)
                    .load(BuildConfig.IMAGE_URL + Constant.image_size_185 + favorite.posterPath)
                    .centerCrop()
                    .into(favorite_poster)
                favorite_overview.text =
                    favorite.overview ?: context.getString(R.string.no_translations)
                val title =
                    favorite.title + " (" + Simplify.getYear(favorite.releaseDate ?: "2019") + ")"
                favorite_title.text = title
            }
        }
    }
}