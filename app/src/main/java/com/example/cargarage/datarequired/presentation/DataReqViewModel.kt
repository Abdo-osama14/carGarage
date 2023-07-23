package com.example.cargarage.datarequired.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cargarage.datarequired.data.DataRequiredRepository
import com.example.cargarage.datarequired.domian.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataReqViewModel @Inject constructor(private val dataRequiredRepository: DataRequiredRepository):ViewModel() {

    fun insertData(userModel: UserModel)= run {
        dataRequiredRepository.insertData(userModel)
    }


    fun signOut()=dataRequiredRepository.signOut()

}