package com.zenjob.android.browsr.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val success: Boolean
)