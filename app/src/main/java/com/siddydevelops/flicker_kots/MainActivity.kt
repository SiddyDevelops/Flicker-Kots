package com.siddydevelops.flicker_kots

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.Exception

class MainActivity : AppCompatActivity(), GetRawData.OnDownloadComplete, GetFlickerJSONData.OnDataAvailable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getRawData = GetRawData(this)
        //getRawData.setDownloadCompleteListener(this)
        getRawData.execute("https://api.flickr.com/services/feeds/photos_public.gne?tags=android,oreo&format=json&nojsoncallback=1")

    }

    companion object {
        private val TAG = "MainActivity"
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if(status == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete called, data is $data")
            val getFlickerJSONData = GetFlickerJSONData(this)
            getFlickerJSONData.execute(data)
        } else {
            Log.d(TAG, "onDownloadComplete failed, with status $status. Error message data is: $data")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG, "onDataAvailable called, data is $data")
        Log.d(TAG, "onDataAvailable end")
    }

    override fun onError(exception: Exception) {
        Log.d(TAG, "onError called, Exception: ${exception.message}")
    }

}