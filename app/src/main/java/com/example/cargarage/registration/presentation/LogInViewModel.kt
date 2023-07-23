package com.example.cargarage.registration.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cargarage.registration.data.AuthSystemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val authSystemRepository: AuthSystemRepository):ViewModel() {

    val isLogIn=MutableLiveData<Boolean>()
    val isEmailVerified=MutableLiveData<Boolean>()
    val isUserHaveData=MutableLiveData<Boolean>()
    val exception=MutableLiveData<String>()

    fun signIn(email:String,password:String)=run {
        authSystemRepository.signIn(email,password).addOnCompleteListener {
            isLogIn.value=it.isSuccessful
        }
    }
    fun checkEmailVerification()=run {
        isEmailVerified.value = authSystemRepository.checkEmailVerification()
    }
    fun signOut()=authSystemRepository.signOut()

    fun getData()=run {
        authSystemRepository.getData().addOnSuccessListener {
            isUserHaveData.value = !it.isEmpty
        }.addOnFailureListener { ex->
            exception.value=ex.toString()
        }
    }
}