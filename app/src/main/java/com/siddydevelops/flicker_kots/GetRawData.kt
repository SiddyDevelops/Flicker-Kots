package com.siddydevelops.flicker_kots

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus {
    OK, IDLE, NOT_INITIALISED, FAILED_OR_EMPTY, PERMISSIONS_ERROR, ERROR
}

class GetRawData : AsyncTask<String, Void, String>() {
    private val TAG = "GetRawData"
    private var downloadStatus =  DownloadStatus.IDLE

    override fun doInBackground(vararg params: String?): String {
        if(params[0] == null) {
            downloadStatus = DownloadStatus.NOT_INITIALISED
            return "No URL specified"
        }
        try {
            downloadStatus = DownloadStatus.OK
            return URL(params[0]).readText()
        } catch(e:Exception) {
            val errorMessage = when(e) {
                is MalformedURLException -> {
                    downloadStatus = DownloadStatus.NOT_INITIALISED
                    "doInBackground: Invalid URL ${e.message}"
                }
                is IOException -> {
                    downloadStatus = DownloadStatus.FAILED_OR_EMPTY
                    "doInBackground: IOException reading data: ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus = DownloadStatus.PERMISSIONS_ERROR
                    "doInBackground: Security Exception: Needs permission? ${e.message}"
                }
                else -> {
                    downloadStatus = DownloadStatus.ERROR
                    "doInBackground: Unknown Error: ${e.message}"
                }
            }
            Log.e(TAG, errorMessage)
            return errorMessage
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

}