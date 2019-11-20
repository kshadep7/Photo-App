package com.akash.flickrbrowser

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "RecycleVItemClickListnr"

class RecyclerViewItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val listener: OnRecyclerItemClickListener
) : RecyclerView.SimpleOnItemTouchListener() {

    interface OnRecyclerItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongPress(view: View, position: Int)
    }

    // Adding Gesture Detector
    private val gestureDetector =
        GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {

            override fun onSingleTapUp(e: MotionEvent): Boolean {
                Log.d(TAG, "onSingleTapUp called")
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                Log.d(TAG, "onSingleTapUp calling .onItemClick()")
                if (childView != null) {
                    listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
                }

//            return super.onSingleTapUp(e)
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                Log.d(TAG, "onLongPress called")
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                Log.d(TAG, "onLongPress calling .onItemClick()")
                if (childView != null) {
                    listener.onItemLongPress(
                        childView,
                        recyclerView.getChildAdapterPosition(childView)
                    )
                }
//                super.onLongPress(e)
            }
        })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, "onInterceptTouchEvent: called start")
//        return super.onInterceptTouchEvent(rv, e)
        val result = gestureDetector.onTouchEvent(e)
        Log.d(TAG, "onInterceptTouchEvent: returning $result")
        return result
    }
}