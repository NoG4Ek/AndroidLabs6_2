package com.example.lab6_2

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab6_2.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import java.io.BufferedInputStream
import java.net.URL


class MainActivity : AppCompatActivity() {
    private lateinit var executor: ExecutorService
    private lateinit var binding: ActivityMainBinding
    private val bitmapData = MutableLiveData<Bitmap>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (binding.imgView.drawable == null) {
            loadImageFromNet()
        }

        bitmapData.observe(this) { value ->
            if (value != null) {
                binding.imgView.setImageBitmap(value)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }

    private fun loadImageFromNet() {
        executor = Executors.newFixedThreadPool(1)
        executor.execute {
            val url = URL("https://cdnuploads.aa.com.tr/uploads/Contents/2019/10/24/thumbs_b_c_fb8263ce4f9f43ebdc7634b0d1eb0a08.jpg?v=115427")
            val stream = url.openConnection().getInputStream()
            stream.use {
                val bitmap = BitmapFactory.decodeStream(stream)
                bitmapData.postValue(bitmap)
            }
        }
    }
}