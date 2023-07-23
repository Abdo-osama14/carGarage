package com.example.cargarage.registration.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cargarage.registration.data.AuthSystemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor (private val authSystemRepository: AuthSystemRepository):ViewModel() {

val isSignUP=MutableLiveData<Boolean>()
 val isEmailSend= MutableLiveData<Boolean>()

fun signUp(email:String,password:String)= run {
    authSystemRepository.signUp(email,password).addOnCompleteListener {
        isSignUP.value = it.isSuccessful
    }
}
    fun sendEmailVerification()=run {
        authSystemRepository.sendEmailVerification()?.addOnCompleteListener {
            isEmailSend.value=it.isSuccessful
    }}
    fun signOut()=authSystemRepository.signOut()
}