package com.akash.flickrbrowser

import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

private const val TAG = "BaseActivity"
internal const val FLICKR_QUERY = "FLICKR_QUERY"
internal const val PHOTO_TRANSFER = "PHOTO_TRANSFER"

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {

    internal fun activateToolbar(enableHome: Boolean){
        Log.d(TAG, "activateToolbar? -> ${if(enableHome) "yes" else "no"}")
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }

}