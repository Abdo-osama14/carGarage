package com.example.cargarage.garagemap.data

import com.example.cargarage.garagemap.domain.Slot
import com.google.firebase.database.FirebaseDatabase
import org.checkerframework.checker.units.qual.g
import javax.inject.Inject

class MapRepository @Inject constructor (private val firebaseDatabase: FirebaseDatabase) {

    private fun getSlotsPref()=firebaseDatabase.getReference("Slots")

    fun getSlotsData()= getSlotsPref()
    fun saveSlotData(slotName:String,slot: Slot)=getSlotsPref().child(slotName).setValue(slot)
    fun deleteSlot(slotName: String)=getSlotsData().child(slotName).removeValue()

}