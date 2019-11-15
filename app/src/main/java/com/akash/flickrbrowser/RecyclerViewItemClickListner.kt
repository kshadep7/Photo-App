package com.akash.flickrbrowser

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "RecycleVItemClickListnr"

class RecyclerViewItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val listener: OnRecyclerItemClickListener
) : RecyclerView.SimpleOnItemTouchListener() {

    interface OnRecyclerItemClickListener {
        fun itemClick(view: View, position: Int)
        fun itemLongPress(view: View, position: Int)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, "onInterceptTouchEvent: called $e")
        return super.onInterceptTouchEvent(rv, e)
    }
}