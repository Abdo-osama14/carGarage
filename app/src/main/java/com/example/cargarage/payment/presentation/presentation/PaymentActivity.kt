package com.example.cargarage.payment.presentation.presentation

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.cargarage.R
import com.example.cargarage.payment.presentation.domain.SlotPickedModel
import com.example.cargarage.timeshow.presentation.TimeReserved
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_payment.*
import java.time.LocalTime

@AndroidEntryPoint
class PaymentActivity : AppCompatActivity() {

    private lateinit var viewModel: PaymentViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        viewModel=ViewModelProvider(this)[PaymentViewModel::class.java]

        buttonPayNow.setOnClickListener {
            val cardNumber=editTextCardNumber.text.toString()
            val expiryDate=editTextExpiryDate.text.toString()
            val cvv=editTextCVV.text.toString()
            val name=editTextFullName.text.toString()
            val address=editTextAddress.text.toString()
            val city= editTextCity.text.toString()
            val payAmount=editTextPaymentAmount.text.toString()
            val time=editTextTime.text.toString()
            if (cardNumber.isEmpty()||expiryDate.isEmpty()||cvv.isEmpty()||name.isEmpty()||address.isEmpty()||city.isEmpty()||payAmount.isEmpty()||time.isEmpty()){
                Toast.makeText(this,"please fail all fields",Toast.LENGTH_LONG).show()
            }
            else{
                checkMoneyPayed(payAmount.toInt(),time.toInt())
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun checkMoneyPayed(money:Int, time:Int){
        val startTime=getCurrentTime()
        val endTime=getEndTime(time)
        if (money<(time*50))
        {
            Toast.makeText(this,"The money you entered is not enough",Toast.LENGTH_LONG).show()
        }
        else{
            val state = "User Is Picked A Slot"
            val slotPickedModel= SlotPickedModel(state,startTime,time)
            viewModel.insertData(slotPickedModel)

            Toast.makeText(this,"Successful payment... The parking lot has been successfully booked",Toast.LENGTH_LONG).show()
            finish()
            Intent(this, TimeReserved::class.java).also {
                it.putExtra("startTime",startTime)
                it.putExtra("endTime",endTime)
                it.putExtra("countDown",time)
                startActivity(it)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime(): String {
        val currentTime = LocalTime.now()
        val hour = currentTime.hour
        val minute = currentTime.minute
        val second = currentTime.second

        return String.format("%02d:%02d:%02d", hour, minute, second)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getEndTime(stayTime:Int): String {
        val currentTime = LocalTime.now()
        val hour = currentTime.hour+stayTime
        val minute = currentTime.minute
        val second = currentTime.second

        return String.format("%02d:%02d:%02d", hour, minute, second)
    }
}