package com.example.cargarage.payment.presentation.data

import com.example.cargarage.payment.presentation.domain.SlotPickedModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class PaymentRepository @Inject constructor(private val fireStore: FirebaseFirestore, private val firebaseAuth: FirebaseAuth) {

    fun insertData(slotPickedModel: SlotPickedModel) =
        fireStore.collection("Slots Picked By ${firebaseAuth.currentUser!!.email}").add(slotPickedModel)

}