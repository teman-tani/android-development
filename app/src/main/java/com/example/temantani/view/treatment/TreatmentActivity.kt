package com.example.temantani.view.treatment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.temantani.R
import com.example.temantani.adapter.FirebaseDiseasesAdapter
import com.example.temantani.adapter.SectionsPagerAdapter
import com.example.temantani.databinding.ActivityMainBinding
import com.example.temantani.databinding.ActivityTreatmentBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.FirebaseDatabase

class TreatmentActivity : AppCompatActivity() {
    companion object {

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.disease,
            R.string.plants
        )
    }

    private lateinit var binding: ActivityTreatmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)


        binding = ActivityTreatmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()


        supportActionBar?.hide()
    }
}