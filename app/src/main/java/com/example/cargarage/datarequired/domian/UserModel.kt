package com.example.cargarage.datarequired.domian

import java.io.Serializable

data class UserModel(
    val name:String="",
    val number:String="",
    val carModel:String="",
    val carNumber:String=""
):Serializable