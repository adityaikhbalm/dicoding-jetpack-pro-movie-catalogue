package com.dicoding.bajp.ui.main.tv

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
import com.dicoding.bajp.utlis.Simplify.getYear
import kotlinx.android.synthetic.main.item_movie_tv.view.*

class TvAdapter(
    private val onClick: (ApiResult) -> Unit
) :
    RecyclerView.Adapter<TvAdapter.Holder>() {

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

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(differ.currentList[position])

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("DefaultLocale")
        fun bind(tv: ApiResult) = with(itemView) {
            detail_movies.setOnClickListener {
                tv.type = "tv"
                onClick(tv)
            }
            Glide.with(context)
                .load(BuildConfig.IMAGE_URL + Constant.image_size_185 + tv.posterPath)
                .centerCrop()
                .into(poster_movies)
            rating_movies.text = tv.voteAverage.toString()
            language_movies.text = tv.originalLanguage?.capitalize()
            val name = tv.name + " (" + getYear(tv.firstAirDate ?: "2019") + ")"
            title_movies.text = name
        }
    }
}