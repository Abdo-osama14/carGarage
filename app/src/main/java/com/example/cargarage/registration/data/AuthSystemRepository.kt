package com.example.cargarage.registration.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


class AuthSystemRepository @Inject constructor (private val firebaseAuth: FirebaseAuth,private val fireStore: FirebaseFirestore) {

    fun signIn( email:String , password:String)=firebaseAuth.signInWithEmailAndPassword(email,password)
    fun checkEmailVerification()= firebaseAuth.currentUser?.isEmailVerified
    fun signUp(email: String,password: String)=firebaseAuth.createUserWithEmailAndPassword(email, password)
    fun sendEmailVerification()=firebaseAuth.currentUser?.sendEmailVerification()
    fun sendPasswordResetEmail(email: String)=firebaseAuth.sendPasswordResetEmail(email)
    fun signOut()=firebaseAuth.signOut()
     fun getData() = fireStore.collection("user${firebaseAuth.currentUser!!.email}").get()
}