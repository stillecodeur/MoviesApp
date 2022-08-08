package com.zenjob.android.browsr.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zenjob.android.browsr.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
    }
}