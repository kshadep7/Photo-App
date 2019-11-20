package com.akash.flickrbrowser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.thumbnail)
    val title: TextView = view.findViewById(R.id.title)
}

private const val TAG = "RecyclerViewAdapter"

class RecyclerViewAdapter(private var photoList: List<Photo>) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//        Log.d(TAG, "onCreateViewHolder: new view requested")
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.photo_object_view, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
//        Log.d(TAG, "getItemCount called")
        return if (photoList.isNotEmpty()) photoList.size else 0
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val photoItem = photoList[position]
//        Log.d(TAG, "onBindViewHolder: ${photoItem.title} --> $position")
        Picasso.get()
            .load(photoItem.image)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.imageView)

        holder.title.text = photoItem.title
    }

    fun loadNewData(newPhotoList: List<Photo>) {
        photoList = newPhotoList
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        return if (photoList.isNotEmpty()) photoList[position] else null
    }
}