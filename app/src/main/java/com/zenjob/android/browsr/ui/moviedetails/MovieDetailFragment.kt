package com.zenjob.android.browsr.ui.moviedetails

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.chip.Chip
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.data.model.ApiResult
import com.zenjob.android.browsr.data.model.Genre
import com.zenjob.android.browsr.data.model.Movie
import com.zenjob.android.browsr.databinding.FragmentMovieDetailBinding
import com.zenjob.android.browsr.ui.moviedetails.viewmodel.MovieDetailsViewModel
import com.zenjob.android.browsr.utils.AppUtils
import com.zenjob.android.browsr.utils.IMAGE_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<MovieDetailsViewModel>()
    private var shouldHideProgress = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        showProgress()
        viewModel.getMovie(args.movieId, "en-US")
        viewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiResult.Success -> setMovieDetails(response.data)
                is ApiResult.Error -> {
                    hideProgress()
                    showEmptyState(response.errorData.statusMessage)
                }
                is ApiResult.Failure -> {
                    hideProgress()
                    showEmptyState(response.t.message.toString())
                }
            }
        }
    }

    private fun setMovieDetails(movie: Movie) {

        setMovieTitle(movie.title)

        movie.overview?.let { overview ->
            setMovieDescription(overview)
        }
        movie.status?.let { status ->
            setReleaseDate(movie.releaseDate.toString(), status)
        }
        movie.voteAverage?.let { voting ->
            setMovieRatings(voting)
        }
        movie.voteCount?.let { voteCount ->
            setMovieVoting(voteCount)
        }
        movie.genres?.let { genres ->
            createGenreChips(genres)
        }
        movie.posterPath?.let { posterUrl ->
            setMoviePoster(posterUrl)
        }
        movie.backdropPath?.let { backdropUrl ->
            setMovieBackdrop(backdropUrl)
        }
    }

    private fun setMovieTitle(title: String) {
        binding.tvTitle.text = title
    }

    private fun setMovieDescription(description: String) {
        binding.tvDescription.text = description
    }

    private fun setMovieRatings(rating: Float) {
        binding.rbVote.rating = rating * 0.5f
        binding.tvRating.text = rating.toString()
    }

    private fun setMovieVoting(voting: Int) {
        binding.tvCount.text = "(${voting.toString()})"
    }

    private fun createGenreChips(genres: List<Genre>) {
        genres.forEach {
            val chip = Chip(requireContext())
            chip.text = it.name
            chip.setChipBackgroundColorResource(R.color.chipBackground)
            chip.setTextAppearance(R.style.ChipTextAppearance);
            binding.chipGenre.addView(chip)
        }
    }

    private fun setReleaseDate(releaseDate: String, status: String) {
        var releaseText = "Released"
        if (status.lowercase() != "released") {
            releaseText = "Releasing"
        }
        binding.tvReleaseDate.text =
            "$releaseText on ${AppUtils.getDate(releaseDate)}"
    }

    private fun setMoviePoster(posterUrl: String) {
        Glide.with(requireContext()).load(IMAGE_URL + posterUrl)
            .placeholder(R.drawable.movie_icon_2)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (shouldHideProgress) {
                        hideProgress()
                    } else {
                        shouldHideProgress = true
                    }
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (shouldHideProgress) {
                        hideProgress()
                    } else {
                        shouldHideProgress = true
                    }
                    return false
                }

            })
            .into(binding.imgPoster)

    }

    private fun setMovieBackdrop(backdropUrl: String) {
        Glide.with(requireContext()).load(IMAGE_URL + backdropUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (shouldHideProgress) {
                        hideProgress()
                    } else {
                        shouldHideProgress = true
                    }
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (shouldHideProgress) {
                        hideProgress()
                    } else {
                        shouldHideProgress = true
                    }
                    return false
                }

            })
            .into(binding.imgBackdrop)
    }

    private fun showEmptyState(msg: String) {
        binding.emptyGroup.isVisible = true
        binding.detailGroup.isVisible = false
        binding.emptyView.text = msg
    }

    private fun showProgress() {
        binding.progress.isVisible = true
    }

    private fun hideProgress() {
        binding.progress.isVisible = false
    }

}