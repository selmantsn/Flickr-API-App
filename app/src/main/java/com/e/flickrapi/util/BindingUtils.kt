package com.e.flickrapi.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.e.flickrapi.models.Photo

@BindingAdapter("itemToImage")
fun loadImage(view: ImageView, item: Photo) {

    val url =
        "https://farm" + item.farm + ".staticflickr.com/" + item.server + "/" + item.id + "_" + item.secret + ".jpg"

    Glide.with(view).load(url)
        .placeholder(android.R.color.background_dark)
        .into(view)

}
