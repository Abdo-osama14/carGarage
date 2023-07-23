package com.example.cargarage.registration.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cargarage.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_forget_password.*
@AndroidEntryPoint
class ForgetPassword : AppCompatActivity() {
private lateinit var viewModel: ForgotPassViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        viewModel=ViewModelProvider(this)[ForgotPassViewModel::class.java]

        gotoLogIn.setOnClickListener {
            Intent(this, LogIn::class.java).also {
                startActivity(it)
            }
        }
        enterEmailBtn.setOnClickListener {
            val email=forgotPasswordEmail.text.toString()
            if (email.isEmpty()){
                Toast.makeText(this,"please enter your email",Toast.LENGTH_LONG).show()
            }
            else{
                viewModel.sendPasswordResetEmail(email)
                viewModel.isPassRestEmilSend.observe(this, Observer {
                    when(it){
                        true -> {
                            Toast.makeText(this,"mail sent, check yor gmail to reset the password",Toast.LENGTH_SHORT).show()
                            finish()
                            Intent(this, LogIn::class.java).also { intent->
                                startActivity(intent)
                            }
                        }
                        false -> Toast.makeText(this,"mail is wrong or not existed",Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

    }
}