package com.example.cargarage.registration.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cargarage.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sin_up.*

@AndroidEntryPoint
 class SinUp : AppCompatActivity() {
     private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sin_up)

        signUpViewModel= ViewModelProvider(this)[SignUpViewModel::class.java]

        tvGoToLogin.setOnClickListener {
            Intent(this, LogIn::class.java).also {
                startActivity(it)
            }
        }
        signUpBtn2.setOnClickListener {
            val email=signUpEmail.text.toString()
            val password=signUpPassword.text.toString()

            if (email.isEmpty()||password.isEmpty()){
                Toast.makeText(this,"please fail all fields",Toast.LENGTH_LONG).show()
            }
            if (password.length<7){
                Toast.makeText(this,"password should be more 7 char",Toast.LENGTH_LONG).show()
            }
            else{
                signUpViewModel.signUp(email,password)
                signUpViewModel.isSignUP.observe(this, Observer {
                   when(it){
                       true-> { Toast.makeText(this,"Registration successful ",Toast.LENGTH_SHORT).show()
                           sendEmailVerification()
                       }
                       false-> Toast.makeText(this,"failed to register",Toast.LENGTH_SHORT).show()
                   }

                })
            }
        }

    }
    private fun sendEmailVerification(){
        signUpViewModel.sendEmailVerification()
        signUpViewModel.isEmailSend.observe(this,Observer{
            if (it==true){
                Toast.makeText(this,"Verification email is sent , verify and Log in ",Toast.LENGTH_SHORT).show()
                signUpViewModel.signOut()
                finish()
            }
        })
    }
}


