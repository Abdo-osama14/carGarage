package com.example.cargarage.garagemap.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cargarage.R
import com.example.cargarage.garagemap.domain.Slot
import kotlinx.android.synthetic.main.item_slot.view.*

class SlotAdapter:RecyclerView.Adapter<SlotAdapter.SlotViewHolder>() {

    inner class SlotViewHolder(itemview: View): RecyclerView.ViewHolder(itemview)

    private val differCallBack=object :DiffUtil.ItemCallback<Slot>(){
        override fun areItemsTheSame(oldItem: Slot, newItem: Slot): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Slot, newItem: Slot): Boolean {
            return oldItem.id==newItem.id
        }
    }

    val differ=AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_slot,parent,false)
        return SlotViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        val slot=differ.currentList[position]
        holder.itemView.apply {
            tvName.text="Slot ${slot.id}"
            if (slot.state=="empty"){
                tvDescription.text="This Slot Is Empty"
                cvSlot.setCardBackgroundColor(Color.GREEN)
                tvClick.text="Click To Pick This Slot"
                setOnClickListener {
                    onItemClickListener?.let { it(slot) }
                }
            }
            else{
                tvDescription.text="This Slot Piked"
                cvSlot.setCardBackgroundColor(Color.RED)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Slot)->Unit)?=null
    fun setOnItemClickListener(listener:(Slot)->Unit){
        onItemClickListener=listener
    }

}