package com.example.temantani.data.api

import com.google.gson.annotations.SerializedName

data class DetectionResponse(

	@field:SerializedName("penyakit")
	val penyakit: String,

	@field:SerializedName("recommendations")
	val recommendations: List<RecommendationsItem>
)

data class RecommendationsItem(

	@field:SerializedName("tempat")
	val tempat: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("image_src")
	val imageSrc: String,

	@field:SerializedName("product_link")
	val productLink: String
)
