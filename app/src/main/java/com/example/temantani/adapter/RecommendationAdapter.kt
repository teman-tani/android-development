package com.example.temantani.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.temantani.R
import com.example.temantani.data.model.Recommendation
import com.example.temantani.databinding.ItemRowDiseasesBinding
import com.example.temantani.databinding.ItemRowImageBinding
import com.example.temantani.databinding.ItemRowRecommendationBinding

class RecommendationAdapter(private val listRecommendation: ArrayList<Recommendation>, private val context: Context) : RecyclerView.Adapter<RecommendationAdapter.ListViewHolder>(){
    class ListViewHolder(val binding:ItemRowRecommendationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_recommendation, parent, false)
        val binding = ItemRowRecommendationBinding.bind(view)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val list = listRecommendation[position]
        holder.binding.apply {
            tvTitleRecommendation.text = list.nama
        }
    }

    override fun getItemCount(): Int = listRecommendation.size
}