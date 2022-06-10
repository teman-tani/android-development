package com.example.temantani.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.temantani.R
import com.example.temantani.data.model.Diseases
import com.example.temantani.databinding.ItemRowDiseasesBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class FirebaseDiseasesAdapter(
    options: FirebaseRecyclerOptions<Diseases>
    ) : FirebaseRecyclerAdapter<Diseases, FirebaseDiseasesAdapter.DiseasesViewHolder>(options) {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): DiseasesViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_diseases, parent, false)
            val binding = ItemRowDiseasesBinding.bind(view)
            return DiseasesViewHolder(binding)
        }

        override fun onBindViewHolder(
            holder: DiseasesViewHolder,
            position: Int,
            model: Diseases
        ) {
            holder.bind(model)
        }

        inner class DiseasesViewHolder(private val binding: ItemRowDiseasesBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Diseases) {
                binding.tvTitleDiseases.text = item.nama
                binding.tvLatinDiseases.text = item.nama_lain
                binding.tvCause.text = item.penyebab
                binding.tvPlants.text = item.tanaman
            }
        }

    }

