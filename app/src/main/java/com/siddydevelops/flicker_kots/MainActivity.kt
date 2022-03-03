package com.siddydevelops.flicker_kots

import android.content.Intent
import android.location.Criteria
import android.net.Uri
import android.nfc.NdefRecord.createUri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception

class MainActivity : BaseActivity(), GetRawData.OnDownloadComplete,
    GetFlickerJSONData.OnDataAvailable, RecyclerItemClickListener.OnRecyclerClickListener  {

    companion object {
        private val TAG = "MainActivity"
    }

    private val flickerRVAdapter = FlickerRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activateToolbar(false)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addOnItemTouchListener(RecyclerItemClickListener(this,recycler_view,this))
        recycler_view.adapter = flickerRVAdapter

        val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne", "android","en-us", true)

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(TAG, "onCreateOptionsMenu called")
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG, "onOptionsItemSelected called")
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
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
        Log.d(TAG, "onDataAvailable called")
        flickerRVAdapter.loadNewData(data)
        Log.d(TAG, "onDataAvailable end")
    }

    override fun onError(exception: Exception) {
        Log.d(TAG, "onError called, Exception: ${exception.message}")
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(this, "Normal tap at position $position", Toast.LENGTH_LONG).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
        //Toast.makeText(this, "Long tap at position $position", Toast.LENGTH_LONG).show()
        val photo = flickerRVAdapter.getPhoto(position)
        if(photo != null){
            val intent = Intent(this, PhotoDetailsActivity::class.java)
            intent.putExtra(PHOTO_TRANSFER, photo)
            startActivity(intent)
        }
    }

}