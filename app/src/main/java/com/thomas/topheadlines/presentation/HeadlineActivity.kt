package com.thomas.topheadlines.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thomas.topheadlines.databinding.HeadlineActivityLayoutBinding

class HeadlineActivity: AppCompatActivity() {

    private lateinit var binding: HeadlineActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HeadlineActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}