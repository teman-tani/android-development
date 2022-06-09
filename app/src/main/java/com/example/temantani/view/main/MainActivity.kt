package com.example.temantani.view.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.temantani.data.local.Data
import com.example.temantani.data.model.MenuCategory
import com.example.temantani.databinding.ActivityMainBinding
import com.example.temantani.databinding.CardMenuBinding
import com.example.temantani.view.detection.CameraActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mapMenuCategory: Map<String, MenuCategory>
    private val viewModel by viewModels<MainViewModel>()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Izin tidak diberikan.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapMenuCategory = viewModel.getMenuCategory()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        supportActionBar?.hide()

        setupLayout()
        setupAction()

    }

    private fun setupLayout() {
        binding.apply {
            setupModuleCard(Data.MENU_DETECTION, miOption0)
            setupModuleCard(Data.MENU_TREATMENT, miOption1)
            setupModuleCard(Data.MENU_SHOP, miOption2)
            setupModuleCard(Data.MENU_FELTILIZER_CALCULATOR, miOption3)
            setupModuleCard(Data.MENU_ASK_EXPERT, miOption4)
            setupModuleCard(Data.MENU_COMMUNITY, miOption5)
        }
    }
    private fun setupModuleCard(mapKey: String, moduleCard: CardMenuBinding) {
        val category = mapMenuCategory[mapKey]
        if (category != null) {
            Glide.with(this@MainActivity)
                .load(category.thumbnail)
                .apply(RequestOptions().override(100, 100))
                .into(moduleCard.ivItemImage)
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun setupAction(){
        binding.apply {
            miOption0.miContainer.setOnClickListener{
                startCameraX()
            }
        }
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val file = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
        }
    }

    companion object {
        const val CAMERA_X_RESULT = 200
        const val EXTRA_TOKEN = "extra_token"

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10

    }

}