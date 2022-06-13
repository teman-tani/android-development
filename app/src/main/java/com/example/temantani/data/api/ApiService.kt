package com.example.temantani.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Multipart
    @POST("resultmodel")
    fun detection(
        @Part file: MultipartBody.Part,
        @Part("api_key") api_key: RequestBody,
    ): Call<DetectionResponse>

    @Multipart
    @POST("converter.php")
    fun test(
        @Part foto: MultipartBody.Part,
    ): Call<TestResponse>
}