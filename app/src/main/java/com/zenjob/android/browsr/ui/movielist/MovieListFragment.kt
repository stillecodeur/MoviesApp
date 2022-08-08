package com.zenjob.android.browsr.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.databinding.FragmentMovieListBinding
import com.zenjob.android.browsr.ui.movielist.adapter.MoviesAdapter
import com.zenjob.android.browsr.ui.movielist.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private val viewModel by viewModels<MovieListViewModel>()
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setLoadStates()
        fetchMovies()
    }

    private fun init() {
        adapter = MoviesAdapter {
            findNavController().navigate(
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                    it.id
                )
            )
        }
        binding.recyclerViewMovies.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchMovies()
        }
    }

    private fun fetchMovies() {
        lifecycleScope.launch {
            viewModel.searchMovies("en-US").catch { t ->
                t.message?.let {
                    showEmptyState(it)
                } ?: showEmptyState(getString(R.string.error_something_went_wrong))
            }
                .collectLatest {
                    adapter.submitData(it)
                }
        }
    }

    private fun setLoadStates() {
        adapter.addLoadStateListener { loadState ->
            when {
                loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount < 1 -> {
                    showEmptyState(getString(R.string.error_no_data))
                }
                loadState.append is LoadState.Error || loadState.refresh is LoadState.Error -> {
                    val e = loadState.refresh as LoadState.Error
                    if (e.error is UnknownHostException) {
                        e.error.message?.let {
                            showEmptyState(it)
                        }
                    } else {
                        showEmptyState(getString(R.string.error_something_went_wrong))
                    }
                }
                loadState.append is LoadState.Loading -> {
                    showProgress()
                }
                loadState.prepend is LoadState.Loading -> {
                    showProgress()
                }
                else -> {
                    hideProgress()
                    hideEmptyState()
                }
            }
        }
    }

    private fun showEmptyState(msg: String) {
        binding.recyclerViewMovies.isVisible = false
        binding.emptyGroup.isVisible = true
        binding.emptyView.text = msg
    }

    private fun hideEmptyState() {
        binding.recyclerViewMovies.isVisible = true
        binding.emptyGroup.isVisible = false
    }

    private fun showProgress(){
        binding.swipeRefreshLayout.isRefreshing = true
    }

    private fun hideProgress(){
        binding.swipeRefreshLayout.isRefreshing = false
    }
}