package com.zenjob.android.browsr.data.api

import com.zenjob.android.browsr.data.model.Movie
import com.zenjob.android.browsr.data.model.PaginatedListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {

    @GET("movie/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String? = null,
        @Query("language") query: String? = null,
        @Query("page") page: Int? = null
    ): PaginatedListResponse<Movie>


    @GET("movie/{movie_id}")
   suspend fun getDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String? = null,
        @Query("language") query: String? = null
    ): Response<Movie>

}
