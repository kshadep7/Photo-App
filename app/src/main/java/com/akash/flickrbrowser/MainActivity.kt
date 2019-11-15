package com.akash.flickrbrowser

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), GetRawData.OnDownloadComplete,
    ParseJsonData.OnDataAvailable, RecyclerViewItemClickListener.OnRecyclerItemClickListener {

    private val recyclerViewAdapter = RecyclerViewAdapter(ArrayList())
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addOnItemTouchListener(
            RecyclerViewItemClickListener(
                this,
                recycler_view,
                this
            )
        )
        recycler_view.adapter = recyclerViewAdapter

        val url =
            createUri(
                "https://www.flickr.com/services/feeds/photos_public.gne",
                "android,oreo",
                "en-us",
                true
            )
        val getRawData = GetRawData(this)
        getRawData.execute(url)
        Log.d(TAG, "onCreate done")

    }

    override fun itemClick(view: View, position: Int) {
        Log.d(TAG, "itemClicked called with view $view and position $position")
        Toast.makeText(this, "Clicked Position $position", Toast.LENGTH_SHORT).show()
    }

    override fun itemLongPress(view: View, position: Int) {
        Log.d(TAG, "itemLongPress called with view $view and position $position")
        Toast.makeText(this, "LongPress Position $position", Toast.LENGTH_SHORT).show()
    }

    private fun createUri(
        baseURL: String,
        searchCriteria: String,
        lang: String,
        matchAll: Boolean
    ): String {
        return Uri.parse(baseURL)
            .buildUpon()
            .appendQueryParameter("tags", searchCriteria)
            .appendQueryParameter("lang", lang)
            .appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
            .appendQueryParameter("nojsoncallback", "1")
            .appendQueryParameter("format", "json")
            .build().toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG, "onCreateOptionsMenu called")
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected called")

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete: Data is gathered")

            val parseJsonData = ParseJsonData(this)
            parseJsonData.execute(data)
        } else {
            //download failed
            Log.d(TAG, "onDownloadComplete: Data download failed -> $data")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG, "onDataAvailable: parsed data")
        recyclerViewAdapter.loadNewData(data)
        Log.d(TAG, "onDataAvailable done")
    }

    override fun onError(e: Exception) {
        Log.d(TAG, "onError called with ${e.message}")
    }


}
