package com.example.cargarage.payment.presentation.domain

import java.io.Serializable

data class SlotPickedModel(
    val state:String?,
    val startTime:String?,
    val pikedTime:Int?
):Serializable