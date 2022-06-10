package com.example.temantani.view.detection

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.temantani.databinding.ActivityResultBinding
import com.example.temantani.utils.bitmapToFile
import com.example.temantani.utils.rotateBitmap
import java.io.File

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


    }



    companion object{
        const val CAMERA_X_RESULT = 200
    }
}