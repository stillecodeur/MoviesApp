package com.zenjob.android.browsr.ui.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zenjob.android.browsr.data.repository.MoviesRepository
import com.zenjob.android.browsr.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    fun searchMovies(queryString: String): Flow<PagingData<Movie>> =
        moviesRepository.getMovieResultsStream(queryString).cachedIn(viewModelScope)

}