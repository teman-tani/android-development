package com.example.temantani.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Diseases(
    val gejala: String? = null,
    val nama: String? = null,
    val nama_lain: String? = null,
    val pencegahan: String? = null,
    val penyebab: String? = null,
    val tanaman: String? = null,
){
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}