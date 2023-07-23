package com.example.cargarage.datarequired.data

import com.example.cargarage.datarequired.domian.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DataRequiredRepository @Inject constructor(private val fireStore: FirebaseFirestore, private val firebaseAuth: FirebaseAuth) {

    fun insertData(userModel: UserModel) = fireStore.collection("user${firebaseAuth.currentUser!!.email}").add(userModel)

    fun signOut()=firebaseAuth.signOut()

}