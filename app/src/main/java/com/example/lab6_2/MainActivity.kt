package com.example.lab6_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.lab6_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (binding.imageView.drawable == null) {
            viewModel.loadImageThread()
        }

        viewModel.bitmapData.observe(this) {
            if (it != null) {
                runOnUiThread {
                    binding.imageView.setImageBitmap(it)
                }
            }
        }

    }
}