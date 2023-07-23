package com.example.cargarage.registration.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cargarage.registration.data.AuthSystemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPassViewModel @Inject constructor(private val authSystemRepository: AuthSystemRepository):ViewModel() {
    val isPassRestEmilSend=MutableLiveData<Boolean>()
    fun sendPasswordResetEmail(email:String)=run {
        authSystemRepository.sendPasswordResetEmail(email).addOnCompleteListener {
            isPassRestEmilSend.value=it.isSuccessful
        }
    }
}