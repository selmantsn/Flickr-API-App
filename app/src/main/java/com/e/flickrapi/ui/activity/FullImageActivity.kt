package com.e.flickrapi.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.e.flickrapi.R
import com.e.flickrapi.databinding.ActivityFullImageBinding
import com.e.flickrapi.models.Photo
import java.io.Serializable

class FullImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_image)

        val photo = intent.extras?.get("photo") as Photo

        binding.photo = photo

    }
}