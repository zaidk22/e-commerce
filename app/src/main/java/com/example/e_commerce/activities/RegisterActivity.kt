package com.example.e_commerce.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.e_commerce.R
import com.example.e_commerce.firestore.FirestoreClass
import com.example.e_commerce.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
       // tv_login.setOnClickListener {
         //   val intent = Intent(this, LoginActivity::class.java)
           // startActivity(intent)
        //}
        auth = FirebaseAuth.getInstance()
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this,isChecked.toString(),Toast.LENGTH_SHORT).show()
        }

        btn_register.setOnClickListener {

            if (et_email.text.toString().isNotEmpty() || et_password.text.toString().isNotEmpty()) {
                Toast.makeText(this, "Input provided", Toast.LENGTH_LONG).show()

            createUser(et_email.text.toString(),et_password.text.toString())
            } else {
                Toast.makeText(this, "Input Required", Toast.LENGTH_LONG).show()
            }

        }



    }

    private fun createUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener (this){ task ->
            if(task.isSuccessful){

                val firebaseUser : FirebaseUser = task.result!!.user!!
                val user = User(
                    firebaseUser.uid,
                    et_firstName.text.toString().trim { it <=' ' },
                    et_lastName.text.toString().trim { it <= ' ' },
                    et_email.text.toString().trim { it <= ' ' }
                )
                FirestoreClass().registerUser(user)

                val intent = Intent(this, UserProfileActivity::class.java)
                startActivity(intent)
            }

             else{
                Log.e("Task Message","Failed")
            }
        }}
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser


    }
}
fun userRegistrationSuccess(){
  // Toast.makeText(this@RegisterActivity,"Registration Successfull",Toast.LENGTH_SHORT).show()



}