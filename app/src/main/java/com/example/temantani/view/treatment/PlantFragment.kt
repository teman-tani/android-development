package com.example.temantani.view.treatment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temantani.R
import com.example.temantani.adapter.FirebaseDiseasesAdapter
import com.example.temantani.adapter.FirebasePlantsAdapter
import com.example.temantani.data.model.Diseases
import com.example.temantani.data.model.Plants
import com.example.temantani.databinding.FragmentDiseasesBinding
import com.example.temantani.databinding.FragmentPlantBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class PlantFragment : Fragment() {
    private var _binding: FragmentPlantBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseDatabase
    private lateinit var adapter: FirebasePlantsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Firebase.database

        val plantRef = db.reference.child(PLANT_CHILD)

        val manager = LinearLayoutManager(view.context)
        binding.rvPlants.layoutManager = manager

        val itemDecoration = DividerItemDecoration(view.context, manager.orientation)
        binding.rvPlants.addItemDecoration(itemDecoration)

        val options = FirebaseRecyclerOptions.Builder<Plants>()
            .setQuery(plantRef, Plants::class.java)
            .build()
        adapter = FirebasePlantsAdapter(options)
        binding.rvPlants.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    public override fun onResume() {
        super.onResume()
        adapter.startListening()
    }
    public override fun onPause() {
        adapter.stopListening()
        super.onPause()
    }

    companion object {
        private val TAG = PlantFragment::class.java.simpleName
        const val ARG_TYPE = "type_follow"
        const val PLANT_CHILD = "tanaman"
    }
}