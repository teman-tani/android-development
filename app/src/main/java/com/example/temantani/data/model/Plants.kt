package com.example.temantani.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Plants(
    val ketinggian: String? = null,
    val nama: String? = null,
    val nama_latin: String? = null,
    val pembenihan: String? = null,
    val pemeliharaan: String? = null,
    val penanaman: String? = null,
    val ph: String? = null,
    val suhu: String? = null,
){
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}