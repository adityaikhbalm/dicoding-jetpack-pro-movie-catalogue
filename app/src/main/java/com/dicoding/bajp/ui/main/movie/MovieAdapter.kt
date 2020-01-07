package com.dicoding.bajp.ui.main.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.bajp.BuildConfig
import com.dicoding.bajp.R
import com.dicoding.bajp.data.remote.response.ApiResult
import com.dicoding.bajp.utlis.Constant
import com.dicoding.bajp.utlis.Simplify
import kotlinx.android.synthetic.main.item_movie_tv.view.*

class MovieAdapter(
    private val onClick: (ApiResult) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.Holder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ApiResult>() {
        override fun areContentsTheSame(oldItem: ApiResult, newItem: ApiResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: ApiResult, newItem: ApiResult): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<ApiResult>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_tv, parent, false)
        )

    override fun getItemViewType(position: Int) = position

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(differ.currentList[position])

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("DefaultLocale")
        fun bind(movie: ApiResult) = with(itemView) {
            detail_movies.setOnClickListener {
                movie.type = "movie"
                onClick(movie)
            }
            Glide.with(context)
                .load(BuildConfig.IMAGE_URL + Constant.image_size_185 + movie.posterPath)
                .centerCrop()
                .into(poster_movies)
            rating_movies.text = movie.voteAverage.toString()
            language_movies.text = movie.originalLanguage?.capitalize()
            val title = movie.title + " (" + Simplify.getYear(movie.releaseDate ?: "2019") + ")"
            title_movies.text = title
        }
    }
}