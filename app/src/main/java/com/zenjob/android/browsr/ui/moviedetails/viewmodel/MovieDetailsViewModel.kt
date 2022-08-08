package com.zenjob.android.browsr.ui.moviedetails.viewmodel

import androidx.lifecycle.*
import com.zenjob.android.browsr.data.repository.MoviesRepository
import com.zenjob.android.browsr.data.model.ApiResult
import com.zenjob.android.browsr.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    private val _response: MutableLiveData<ApiResult<Movie>> = MutableLiveData()
    val response: LiveData<ApiResult<Movie>> = _response

    fun getMovie(movieId: Long, query: String) = viewModelScope.launch {
        moviesRepository.getMovieDetails(movieId, query).catch {t->
            _response.value= ApiResult.Failure(t)
        }
            .collectLatest { values ->
                _response.value = values
            }
    }
}