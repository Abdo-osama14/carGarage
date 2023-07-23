package com.example.cargarage.datarequired.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cargarage.garagemap.presentation.GarageUi
import com.example.cargarage.R
import com.example.cargarage.datarequired.domian.UserModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: DataReqViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel=ViewModelProvider(this)[DataReqViewModel::class.java]

        btnConfirm.setOnClickListener {
            val name= eTName.text.toString()
            val number = eTPhoneNum.text.toString()
            val carModel= eTCarModel.text.toString()
            val cardNumber= eTCarNum.text.toString()

            val userModel=UserModel(name,number,carModel,cardNumber)

            if (name.isEmpty() || number.isEmpty() || carModel.isEmpty() || cardNumber.isEmpty() )
            {
                Toast.makeText(this,"please fail all fields",Toast.LENGTH_LONG).show()
            }
            else{

                viewModel.insertData(userModel)
                Intent(this, GarageUi::class.java).also { intent->
                    startActivity(intent)
                }
            }

        }
    }
}