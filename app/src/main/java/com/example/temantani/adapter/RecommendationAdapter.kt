package com.example.temantani.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.temantani.R
import com.example.temantani.data.model.Recommendation
import com.example.temantani.databinding.ItemRowDiseasesBinding
import com.example.temantani.databinding.ItemRowImageBinding
import com.example.temantani.databinding.ItemRowRecommendationBinding

class RecommendationAdapter(private val listRecommendation: List<Recommendation>) : RecyclerView.Adapter<RecommendationAdapter.ViewHolder>(){

    class ViewHolder(
        var binding: ItemRowRecommendationBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemRowRecommendationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = listRecommendation[position]

        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(category.image_src)
                .apply(RequestOptions().override(100, 100))
                .into(tvPicRecommendation)
            tvTitleRecommendation.text = category.nama
        }
    }

    override fun getItemCount(): Int = listRecommendation.count()
//    class ListViewHolder(val binding:ItemRowRecommendationBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_recommendation, parent, false)
//        val binding = ItemRowRecommendationBinding.bind(view)
//        return ListViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//
//        val list = listRecommendation[position]
//        holder.binding.apply {
//            tvTitleRecommendation.text = list.nama
//        }
//    }
//
//    override fun getItemCount(): Int = listRecommendation.size


}