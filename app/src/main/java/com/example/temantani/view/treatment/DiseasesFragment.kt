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
import com.example.temantani.data.model.Diseases
import com.example.temantani.databinding.FragmentDiseasesBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class DiseasesFragment : Fragment() {
    private var _binding: FragmentDiseasesBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseDatabase
    private lateinit var adapter: FirebaseDiseasesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Firebase.database

        val diseasesRef = db.reference.child(DISEASES_CHILD)

        val manager = LinearLayoutManager(view.context)
        binding.rvDiseases.layoutManager = manager

        val itemDecoration = DividerItemDecoration(view.context, manager.orientation)
        binding.rvDiseases.addItemDecoration(itemDecoration)

        val options = FirebaseRecyclerOptions.Builder<Diseases>()
            .setQuery(diseasesRef, Diseases::class.java)
            .build()
        adapter = FirebaseDiseasesAdapter(options)
        binding.rvDiseases.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiseasesBinding.inflate(inflater, container, false)
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
        private val TAG = DiseasesFragment::class.java.simpleName
        const val ARG_TYPE = "type_follow"
        const val DISEASES_CHILD = "penyakit"
    }


}