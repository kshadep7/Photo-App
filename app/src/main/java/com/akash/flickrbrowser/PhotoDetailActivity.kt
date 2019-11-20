package com.akash.flickrbrowser

import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_photo_detail.*

private const val TAG = "PhotoDetailActivity"
class PhotoDetailActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        activateToolbar(true)

//        val photo = intent.getSerializableExtra(PHOTO_TRANSFER) as Photo?
        val photo = intent.getParcelableExtra<Photo>(PHOTO_TRANSFER)
        Log.d(TAG, "Deserialize photo object: $photo ")
        authorName.text = photo?.author
        title_photo.text = photo?.title
        tags_photo.text = photo?.tags
        Picasso.get()
            .load(photo?.link)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(image_photo)



    }

}
