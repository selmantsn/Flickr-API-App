package com.e.flickrapi.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.flickrapi.R
import com.e.flickrapi.databinding.RowImageBinding
import com.e.flickrapi.models.Photo
import com.e.flickrapi.ui.activity.FullImageActivity


class PhotosAdapter(
    private var items: MutableList<Photo> = arrayListOf()
) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_image,
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        holder.binding.photo = item

        holder.binding.ivImage.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context,FullImageActivity::class.java)
                .putExtra("photo",item)
            )
        }
    }

    fun addAll(list: MutableList<Photo>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(val binding: RowImageBinding) : RecyclerView.ViewHolder(binding.root)

}
