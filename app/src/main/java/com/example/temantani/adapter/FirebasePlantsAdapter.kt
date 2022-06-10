package com.example.temantani.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.temantani.R
import com.example.temantani.data.model.Diseases
import com.example.temantani.data.model.Plants
import com.example.temantani.databinding.ItemRowDiseasesBinding
import com.example.temantani.databinding.ItemRowPlantsBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class FirebasePlantsAdapter(options: FirebaseRecyclerOptions<Plants>)
    : FirebaseRecyclerAdapter<Plants, FirebasePlantsAdapter.PlantsViewHolder>(options)

{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FirebasePlantsAdapter.PlantsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_plants, parent, false)
        val binding = ItemRowPlantsBinding.bind(view)
        return PlantsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FirebasePlantsAdapter.PlantsViewHolder,
        position: Int,
        model: Plants
    ) {
        holder.bind(model)
    }

    inner class PlantsViewHolder(private val binding: ItemRowPlantsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plants) {
            binding.tvTitlePlants.text = item.nama
            binding.tvLatinPlants.text = item.nama_latin
        }
    }
}