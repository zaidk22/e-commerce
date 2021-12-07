package com.example.e_commerce.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.e_commerce.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        button_submit.setOnClickListener {
            if (et_forgot.text.toString().isNotEmpty()) {
                Toast.makeText(this, "Input provided", Toast.LENGTH_LONG).show()
                FirebaseAuth.getInstance().sendPasswordResetEmail(et_forgot.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Log.e("Task Message","Successful")
                        finish()


                    }
                    else{
                        Log.e("Task Message","Failed")
                    }
                }
            }
        }
    }
}