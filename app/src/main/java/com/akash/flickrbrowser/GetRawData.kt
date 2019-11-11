package com.akash.flickrbrowser

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus {
    OK, IDLE, NOT_INITIALIZED, FAILED_OR_EMPTY, PERMISSION_ERROR, ERROR
}

private const val TAG = "GetRawData"

class GetRawData(private val listener: OnDownloadComplete) : AsyncTask<String, Void, String>() {

    private var downloadStatus = DownloadStatus.IDLE
//    private var listener: MainActivity? = null

    interface OnDownloadComplete {
        fun onDownloadComplete(data: String, status: DownloadStatus)
    }

//    fun setDownloadCompleteListener(callBackObject: MainActivity){
//        listener = callBackObject
//    }

    override fun doInBackground(vararg params: String?): String {
        if (params[0] == null) {
            downloadStatus = DownloadStatus.NOT_INITIALIZED
            return "URL is null"
        }

        try {
            downloadStatus = DownloadStatus.OK
            return URL(params[0]).readText()

        } catch (e: Exception) {
            val errorMsg = when (e) {
                is MalformedURLException -> {
                    downloadStatus = DownloadStatus.NOT_INITIALIZED
                    "doInBackground: Invalid URL ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus = DownloadStatus.PERMISSION_ERROR
                    "doInBackground: SecurityException: Permission need! ${e.message}"
                }
                is IOException -> {
                    downloadStatus = DownloadStatus.FAILED_OR_EMPTY
                    "doInBackground: Error reading the data ${e.message}"
                }
                else -> {
                    downloadStatus = DownloadStatus.ERROR
                    "Unknown error ${e.message}"
                }
            }
            Log.e(TAG, errorMsg)

            return errorMsg
        }
    }

    override fun onPostExecute(result: String) {
//        super.onPostExecute(result)

        Log.d(TAG, "onPostExecute: Data : $result")
        listener.onDownloadComplete(result, downloadStatus)
    }
}