package com.example.cargarage.garagemap.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cargarage.garagemap.data.MapRepository
import com.example.cargarage.garagemap.domain.Slot
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor (private val mapRepository: MapRepository) :ViewModel() {

    val slotsList = MutableLiveData<MutableList<Slot>>()
    val isSlotSave = MutableLiveData<Boolean>()
    val isSlotDeleted = MutableLiveData<Boolean>()

     val data : MutableList<Slot> = mutableListOf()

    fun getSlotsData()=run {
        mapRepository.getSlotsData().addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (slotSnapShot in snapshot.children){
                        val slot = slotSnapShot.getValue(Slot::class.java)
                        data.add(slot!!)
                    }
                    slotsList.value=data
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun saveNewSlot(slotName:String,slot: Slot)=run {

       mapRepository.saveSlotData(slotName,slot).addOnSuccessListener {
           isSlotSave.value=true

       }.addOnFailureListener {

       }

    }

    fun deleteOldSlot(slotName: String)=run {
        mapRepository.deleteSlot(slotName).addOnSuccessListener {
            isSlotDeleted.value=true
        }.addOnFailureListener {

        }
    }

}