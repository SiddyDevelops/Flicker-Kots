package com.siddydevelops.flicker_kots

import android.location.Criteria
import android.net.Uri
import android.nfc.NdefRecord.createUri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.Exception

class MainActivity : AppCompatActivity(), GetRawData.OnDownloadComplete, GetFlickerJSONData.OnDataAvailable {

    companion object {
        private val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne", "android,oreo","en-us", true)

        val getRawData = GetRawData(this)
        //getRawData.setDownloadCompleteListener(this)
        getRawData.execute(url)

    }

    private fun createUri(baseUrl: String, searchCriteria: String, lang: String, matchAll: Boolean): String {
        Log.d(TAG, "createUri starts")
        val uri = Uri.parse(baseUrl).buildUpon()
            .appendQueryParameter("tags", searchCriteria)
            .appendQueryParameter("tagmode", if(matchAll) "ALL" else "ANY")
            .appendQueryParameter("lang", lang)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1").build()

        return uri.toString()
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