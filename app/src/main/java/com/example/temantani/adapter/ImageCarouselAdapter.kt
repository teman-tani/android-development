package com.example.temantani.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.temantani.data.model.ImageCarousel
import com.example.temantani.databinding.ItemRowImageBinding

class ImageCarouselAdapter(private var imageList: List<ImageCarousel>) : RecyclerView.Adapter<ImageCarouselAdapter.ImageViewHolder>() {
    class ImageViewHolder(val binding:ItemRowImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemRowImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val carousel = imageList[position]
        holder.binding.apply {
            Glide.with(caraouselImage).load(carousel.image).into(caraouselImage)
        }
    }

    override fun getItemCount(): Int = imageList.size
}