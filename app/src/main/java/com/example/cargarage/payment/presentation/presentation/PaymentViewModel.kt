package com.example.cargarage.payment.presentation.presentation

import androidx.lifecycle.ViewModel
import com.example.cargarage.payment.presentation.data.PaymentRepository
import com.example.cargarage.payment.presentation.domain.SlotPickedModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val paymentRepository: PaymentRepository):ViewModel() {

    fun insertData(slotPickedModel: SlotPickedModel)= run {
        paymentRepository.insertData(slotPickedModel)
    }


}