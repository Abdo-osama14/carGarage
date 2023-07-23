package com.example.cargarage.garagemap.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cargarage.R
import com.example.cargarage.payment.presentation.presentation.PaymentActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_garage_ui.*

@AndroidEntryPoint
class GarageUi : AppCompatActivity() {

    private lateinit var viewModel: MapViewModel
    private lateinit var slotAdapter: SlotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garage_ui)

        setupAdapter()
        viewModel=ViewModelProvider(this)[MapViewModel::class.java]



        viewModel.getSlotsData()
        viewModel.slotsList.observe(this, Observer {
            if (it.isNotEmpty()){
                slotAdapter.differ.submitList(it)
            }
        })
        slotAdapter.setOnItemClickListener {
            Intent(this, PaymentActivity::class.java).also { intent->
                startActivity(intent)
            }
        }
    }

    fun setupAdapter(){
        slotAdapter= SlotAdapter()
        rvMap.apply {
            adapter=slotAdapter
            layoutManager=LinearLayoutManager(this@GarageUi)
        }

    }
}


