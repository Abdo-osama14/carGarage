package com.example.cargarage.registration.presentation


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cargarage.garagemap.presentation.GarageUi
import com.example.cargarage.datarequired.presentation.MainActivity
import com.example.cargarage.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_log_in.*

@AndroidEntryPoint
class LogIn : AppCompatActivity() {

    private lateinit var viewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        viewModel=ViewModelProvider(this)[LogInViewModel::class.java]

        signUpBtn.setOnClickListener {
            Intent(this, SinUp::class.java).also {
                startActivity(it)
            }
        }
        forgotPass.setOnClickListener {
            Intent(this, ForgetPassword::class.java).also {
                startActivity(it)
            }
        }
        logInBtn.setOnClickListener {
            val email=logInEmail.text.toString()
            val password=logInPassword.text.toString()
            if (email.isEmpty()||password.isEmpty()){
                Toast.makeText(this,"please fail all fields", Toast.LENGTH_LONG).show()
            }
            else{
                viewModel.signIn(email,password)
                viewModel.isLogIn.observe(this, Observer {
                    when(it){
                      true -> checkEmailVerification()
                      false ->  Toast.makeText(this,"account not exist", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
    private fun checkEmailVerification(){
        viewModel.checkEmailVerification()
        viewModel.isEmailVerified.observe(this, Observer {
            when(it){
                true -> {  Toast.makeText(this,"Log In", Toast.LENGTH_SHORT).show()
                    avoidDataRepeated()
                   }
                false-> {
                    Toast.makeText(this,"verify your email first", Toast.LENGTH_SHORT).show()
                    viewModel.signOut()
                }
            }
        })
    }
    private fun avoidDataRepeated(){
        viewModel.getData()
        viewModel.isUserHaveData.observe(this, Observer {
            if (it==true){
                finish()
                Intent(this, GarageUi::class.java).also { intent->
                    startActivity(intent)
                }
            }else{
                finish()
                Intent(this, MainActivity::class.java).also { intent->
                    startActivity(intent)
                }
            }
        })
    }
}