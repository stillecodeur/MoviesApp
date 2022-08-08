package com.zenjob.android.browsr.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.zenjob.android.browsr.data.model.ApiResult
import com.zenjob.android.browsr.data.model.ErrorResponse
import retrofit2.Response
import java.lang.NullPointerException


fun Fragment.showToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun <T : Any> Response<T>.flowResult(): ApiResult<T> {
    try {
        if (this.code() == 200) {
            val body = this.body()
            body?.let {
                return ApiResult.Success(it)
            }
        } else {
            val erroBody = this.errorBody()
            erroBody?.let {
                return ApiResult.Error(Gson().fromJson(it.string(), ErrorResponse::class.java))
            }
        }
    } catch (e: Exception) {
        return ApiResult.Failure(e)
    }
    return ApiResult.Failure(NullPointerException())
}

