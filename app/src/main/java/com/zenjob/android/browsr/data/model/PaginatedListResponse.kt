package com.zenjob.android.browsr.data.model

import com.google.gson.annotations.SerializedName


data class PaginatedListResponse<T>(
    val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<T>
)

