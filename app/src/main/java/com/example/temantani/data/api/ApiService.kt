package com.example.temantani.data.api

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("resultmodel")
    fun detection(
        @Part file: MultipartBody.Part,
    ): Call<DetectionResponse>

    @Multipart
    @POST("test")
    fun test(
        @Part img: MultipartBody.Part,
    ): Call<TestResponse>
}