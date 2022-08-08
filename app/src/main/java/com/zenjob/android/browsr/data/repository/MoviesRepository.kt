package com.zenjob.android.browsr.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zenjob.android.browsr.BuildConfig
import com.zenjob.android.browsr.data.model.ApiResult
import com.zenjob.android.browsr.data.model.Movie
import com.zenjob.android.browsr.data.api.MoviesPagingSource
import com.zenjob.android.browsr.data.api.TMDBApiService
import com.zenjob.android.browsr.utils.flowResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MoviesRepository(private val apiService: TMDBApiService) {

    fun getMovieResultsStream(query: String): Flow<PagingData<Movie>> {
        return Pager(
                config = PagingConfig(
                    pageSize = 25,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { MoviesPagingSource(apiService, query) }
            ).flow
    }

    suspend fun getMovieDetails(movieId: Long, query: String): Flow<ApiResult<Movie>> {
        return flow<ApiResult<Movie>> {
            emit(
                apiService.getDetails(movieId = movieId, BuildConfig.TMDB_API_KEY, query = query)
                    .flowResult()
            )
        }.flowOn(Dispatchers.IO)
    }

}