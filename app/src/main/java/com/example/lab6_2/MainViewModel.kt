package com.example.lab6_2

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.net.URL
import java.util.concurrent.ExecutorService

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val executor: ExecutorService = getApplication<App>().executorService
    val bitmapData = MutableLiveData<Bitmap>()

    fun loadImageThread() {
        executor.execute {
            val url = URL("https://s0.tchkcdn.com/g-AL8YW4DnCSBs2uKwgjAEYA/9/26593/660x0/w/0/192412.jpeg")
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            bitmapData.postValue(bitmap)
        }
    }

    override fun onCleared() {
        executor.shutdown()
        super.onCleared()
    }
}
