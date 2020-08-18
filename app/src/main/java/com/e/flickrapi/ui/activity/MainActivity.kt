package com.e.flickrapi.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.flickrapi.R
import com.e.flickrapi.databinding.ActivityMainBinding
import com.e.flickrapi.ui.adapter.PhotosAdapter
import com.e.flickrapi.ui.viewmodel.PhotosViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: PhotosAdapter
    private val viewModel: PhotosViewModel by lazy { ViewModelProvider(this)[PhotosViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        viewModel.getPhotos().observe(this@MainActivity, Observer {
            if (it !=null){
                adapter = PhotosAdapter(it.photos.photo.toMutableList())
                binding.recPhotos.layoutManager =
                    LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                binding.recPhotos.adapter = adapter
            }
        })



        binding.recPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                        viewModel.getPhotos().observe(this@MainActivity, Observer {
                            adapter.addAll(it.photos.photo.toMutableList())
                        })


                }
            }
        })
    }
}