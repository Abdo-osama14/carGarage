package com.example.cargarage.timeshow.presentation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.cargarage.R
import com.example.cargarage.garagemap.presentation.GarageUi
import kotlinx.android.synthetic.main.activity_time_reserved.*

class TimeReserved : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_reserved)

        var currentTime=intent.getStringExtra("startTime")
        var endTime=intent.getStringExtra("endTime")
        var hoursCountDawn=intent.getIntExtra("countDown",1)

        tvShow1.text=currentTime
        tvShow2.text=endTime
        startCountdown(hoursCountDawn)

        btnReturn.setOnClickListener {
            Intent(this, GarageUi::class.java).also {
                it.putExtra("done","yes")
                startActivity(it)
            }
        }

    }
    private fun startCountdown(hours: Int) {

        val countdownMillis = hours * 60 * 60 * 1000L

        val countDownTimer = object : CountDownTimer(countdownMillis, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000) % 60
                val minutes = (millisUntilFinished / (1000 * 60)) % 60
                val hours = (millisUntilFinished / (1000 * 60 * 60)) % 24

                tvShow3.text=(" $hours : $minutes : $seconds ")
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                tvShow3.text=("your picked time finished")
            }
        }

        countDownTimer.start()
    }
}