package com.example.temantani.view.detection

import android.app.Dialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.temantani.R
import com.example.temantani.data.api.ApiConfig
import com.example.temantani.data.api.DetectionResponse
import com.example.temantani.data.api.TestResponse
import com.example.temantani.data.model.Recommendation
import com.example.temantani.databinding.ActivityCameraBinding
import com.example.temantani.utils.bitmapToFile
import com.example.temantani.utils.createFile
import com.example.temantani.utils.reduceFileImage
import com.example.temantani.utils.rotateBitmap
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private var getFile: File? = null
    private var _isAddStorySuccess : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.captureImage.setOnClickListener { takePhoto() }

    }

    public override fun onResume() {
        super.onResume()
        hideSystemUI()
        startCamera()
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val photoFile = createFile(application)
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Toast.makeText(
                        this@CameraActivity,
                        "Gagal mengambil gambar.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val dialog= Dialog(this@CameraActivity)
                    dialog.setCancelable(false)
                    dialog.setContentView(R.layout.dialog_preview)

                    val result = rotateBitmap(
                        BitmapFactory.decodeFile(photoFile.path),
                        cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
                    )

                    getFile = bitmapToFile(result, "Android/media/com.example.temantani/TemanTani/" + photoFile.name) as File
                    dialog.findViewById<ImageView>(R.id.previewImageDetection).setImageBitmap(result)

                    val btn_no=dialog.findViewById<TextView>(R.id.button_no)
                    val btn_yes=dialog.findViewById<TextView>(R.id.button_yes)

                    btn_no.setOnClickListener {
                        dialog.dismiss()
                    }

                    btn_yes.setOnClickListener {
                        uploadImage()
                        finish()
                        dialog.dismiss()
                    }
                    dialog.show()

                }
            }
        )
    }

    private fun uploadImage() {
        if (getFile == null) {
            Toast.makeText(
                this,
                "Silakan masukan berkas gambar terlebih dahulu.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val file = reduceFileImage(getFile as File)

        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())

        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "img",
            file.name,
            requestImageFile
        )

        val description = getString(R.string.api_key).toRequestBody("text/plain".toMediaType())
        addStory(file,imageMultipart,description)
    }

    private fun addStory(
        file : File,
        photo: MultipartBody.Part,
        api_key: RequestBody,
    ) {
        val client =
            ApiConfig.getApiService().detection(photo,api_key)
        client.enqueue(object : Callback<DetectionResponse> {
            override fun onResponse(
                call: Call<DetectionResponse>,
                response: Response<DetectionResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _isAddStorySuccess = _isAddStorySuccess.plus(1)

                    val intent = Intent(this@CameraActivity, ResultActivity::class.java)
                    intent.putExtra("picture", file)
                    intent.putExtra("penyakit", responseBody.penyakit)
                    val listRecommendation =  ArrayList<Recommendation>()
                    for (review in listRecommendation) {
                      intent.putExtra("extra_recommendation", review)
                    }
                    startActivity(intent)

                } else {
                    Log.e(TAG, "BELUM SUCCESS : ${response.message()}")
                    _isAddStorySuccess = 0
                }
            }

            override fun onFailure(call: Call<DetectionResponse>, t: Throwable) {
                Log.e(TAG, "ON FAILURE : ${t.message}")
                _isAddStorySuccess = 0
            }
        })
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (exc: Exception) {
                Toast.makeText(
                    this@CameraActivity,
                    "Gagal menampilkan kamera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }



    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    companion object {
        private const val TAG = "CameraActivity"
    }
}