package com.zenjob.android.browsr.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.data.model.Movie
import com.zenjob.android.browsr.databinding.MovieRowLayoutBinding
import com.zenjob.android.browsr.utils.AppUtils
import com.zenjob.android.browsr.utils.IMAGE_URL

class MoviesAdapter(private val OnClick: (Movie) -> Unit) :
    PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {

    inner class MovieViewHolder(val movieRowLayoutBinding: MovieRowLayoutBinding) :
        RecyclerView.ViewHolder(movieRowLayoutBinding.root)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        with(holder) {
            with(getItem(position)) {
                movieRowLayoutBinding.tvTitle.text = this?.title
                movieRowLayoutBinding.tvDescription.text = this?.overview
                this?.voteAverage?.let {
                    movieRowLayoutBinding.rbVote.rating = it*0.5f
                    movieRowLayoutBinding.tvRating.text=it.toString()
                }

                this?.voteCount?.let {
                    movieRowLayoutBinding.tvCount.text="(${it.toString()})"
                }

                var releaseText="Released"
                this?.status?.let {
                    if(it.lowercase()!="released"){
                        releaseText="Releasing"
                    }
                }
                movieRowLayoutBinding.tvReleaseDate.text="$releaseText on ${AppUtils.getDate(this?.releaseDate.toString())}"
                Glide.with(holder.itemView.context).load(IMAGE_URL + this?.posterPath).placeholder(R.drawable.movie_icon_2)
                    .into(movieRowLayoutBinding.imgPoster)

                movieRowLayoutBinding.root.setOnClickListener {
                    this?.let(OnClick)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}