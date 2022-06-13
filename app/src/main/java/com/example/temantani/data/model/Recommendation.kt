package com.example.temantani.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recommendation(
    val tempat: String?,
    val nama: String?,
    val image_src: String?,
    val product_link : String?
) : Parcelable