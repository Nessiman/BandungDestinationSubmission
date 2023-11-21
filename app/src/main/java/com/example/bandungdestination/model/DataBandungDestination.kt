package com.example.bandungdestination.model

data class DataBandungDestination (
    val id: Int,
    val name: String,
    val rating: Double,
    val image: Int,
    val price: Int,
    val location: String,
    val deskripsi: String,
    var isFavorite: Boolean = false
)