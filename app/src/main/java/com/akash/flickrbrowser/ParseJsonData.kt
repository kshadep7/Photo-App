package com.akash.flickrbrowser

import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject

private const val TAG = "ParseJsonData"

class ParseJsonData(private val listener: OnDataAvailable) :
    AsyncTask<String, Void, ArrayList<Photo>>() {

    interface OnDataAvailable {
        fun onDataAvailable(data: List<Photo>)
        fun onError(e: Exception)
    }

    override fun onPostExecute(result: ArrayList<Photo>) {
        Log.d(TAG, "onPostExecute called")
        super.onPostExecute(result)
        listener.onDataAvailable(result)
    }

    override fun doInBackground(vararg params: String?): ArrayList<Photo> {
        Log.d(TAG, "doInBackground called")
        val photoList = ArrayList<Photo>()
        try {
            val jsonObject = JSONObject(params[0])
            val photoArray = jsonObject.getJSONArray("items")

            for (i in 0 until photoArray.length()) {
                val photo = photoArray.getJSONObject(i)
                val title = photo.getString("title")
                val media = photo.getJSONObject("media")
                val image = media.getString("m")
                val link = image.replaceFirst("_m.jpg", "_b.jpg")
                val author = photo.getString("author")
                val authorId = photo.getString("author_id")
                val tags = photo.getString("tags")

                val photoObject = Photo(title, link, image, author, authorId, tags)

                photoList.add(photoObject)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.d(TAG, "doInBackground: JSONException -> ${e.message}")
            cancel(true)
            listener.onError(e)

        }

        Log.d(TAG, "doInBackground done")
        return photoList
    }
}