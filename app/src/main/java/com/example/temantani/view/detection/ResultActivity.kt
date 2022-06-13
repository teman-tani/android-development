package com.example.temantani.view.detection

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temantani.adapter.RecommendationAdapter
import com.example.temantani.data.model.Recommendation
import com.example.temantani.data.repository.DataRepository
import com.example.temantani.databinding.ActivityResultBinding
import com.example.temantani.utils.bitmapToFile
import com.example.temantani.utils.rotateBitmap
import java.io.File

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var getFile: File? = null

    private var listReview: ArrayList<Recommendation> = ArrayList()
    private lateinit var adapter: RecommendationAdapter

    private lateinit var listRecommendation: List<Recommendation>

    private val repository = DataRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val manager = LinearLayoutManager(this)
//        binding.rvRecommendation.layoutManager = manager

        binding.rvRecommendation.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                true
            )
        )


//        val itemDecoration = DividerItemDecoration(this, manager.orientation)
//        binding.rvRecommendation.addItemDecoration(itemDecoration)

        supportActionBar?.hide()


        val file = intent.getSerializableExtra("picture") as File
        val result = rotateBitmap(
            BitmapFactory.decodeFile(file.path),
            true
        )
        val penyakit = intent.getStringExtra("penyakit")

        getFile = bitmapToFile(result, "Android/media/com.example.temantani/TemanTani/" + file.name) as File

        binding.previewImage.setImageBitmap(result)
        binding.tvDiseases.text = penyakit
        val gejala : String

        if(penyakit == "Leaf Blast"){
            gejala = " bercak coklat gelap, belah ketupat, pusat bercak putih"
        }else if(penyakit == "Brown Spot"){
            gejala = "bercak berwarna coklat tua, berbentuk oval sampai bulat, berukuran sebesar biji wijen, merata di permukaan daun dengan titik tengah berwarna abu-abu atau putih."
        }else{
            gejala = "Garis-garis paralel atau bercak-bercak berwarna putih di sepanjang sumbu utama daun. Bercak putih yang tidak teratur. Layu daun. Kumbang berwarna biru tua atau kehitaman, agak berbentuk persegi, berduri."
        }

        binding.tvCauseResult.text = gejala

        listRecommendation = repository.getRecommendation()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val mAdapter = RecommendationAdapter(listRecommendation)

//        mAdapter.setOnItemClickCallback(object : ModuleCategoryAdapter.OnItemClickCallback{
//            override fun onItemClicked(moduleCategory: ModuleCategory) {
//                val destination = ModuleCategoryFragmentDirections
//                    .actionModuleCategoryFragmentToModuleItemFragment()
//                destination.categoryKey = moduleCategory.key
//                findNavController().navigate(destination)
//            }
//        })

        binding.rvRecommendation.apply {
            adapter = mAdapter
        }
    }


    companion object{
        const val EXTRA_RECOMMENDATION = "extra_recommendation"
        const val CAMERA_X_RESULT = 200
    }
}