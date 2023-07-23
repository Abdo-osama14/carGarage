package com.example.cargarage.garagemap.domain

import java.io.Serializable

data class Slot(
    val id: Int?=null,
    val state:String?=null)
    :Serializable